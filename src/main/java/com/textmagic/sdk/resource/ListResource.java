package com.textmagic.sdk.resource;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;

import static com.textmagic.sdk.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public abstract class ListResource<T extends Resource, C extends RestClient> extends Resource<C> implements Iterable<T> {

	private class ListIterator implements Iterator<T> {

		private Iterator<T> itr;

		public ListIterator(Iterator<T> itr) {
			this.itr = itr;
		}

		public boolean hasNext() {
			return itr.hasNext() || hasNextPage();
		}

		public T next() {
			if (itr.hasNext()) {
				return itr.next();
			}

			try {
				fetchNextPage();
			} catch (RestException e) {
				throw new ClientException(e);
			}

			itr = pageData.iterator();
			return itr.next();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

   /**
    * Page data
    */
	protected List<T> pageData;

   /**
    * Page
    */
	protected Integer page = 1;
    
   /**
    * Limit
    */
	protected Integer limit = 10;

   /**
    * Page count
    */
	protected Integer pageCount = 0;    
    
    /**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 */
	public ListResource(final C client) {
		this(client, new HashMap<String, String>());
	}

	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 * @param parameters Resource parameters
	 */
	public ListResource(final C client, Map<String, String> parameters) {
		super(client);
		this.parameters = parameters;
	}

	/**
	 * Iterator
	 */
    public Iterator<T> iterator() {
		try {
			return new ListIterator(getPageData().iterator());
		} catch (RestException e) {
			throw new ClientException(e);
		}
	}

	/**
	 * Check for next page
	 *
	 * @return true, if successful
	 */
	protected boolean hasNextPage() {
		return page < pageCount;
	}

	/**
	 * Get items list of resource
	 *
	 * @throws RestException exception
	 */
    @SuppressWarnings("unchecked")
	protected void getListContent() throws RestException, ClientException {
		parameters.put("page", Integer.toString(this.page));
        parameters.put("limit", Integer.toString(this.limit));
		
		StringBuilder sb = new StringBuilder();
		sb.append(getResourcePath());
		if (parameters.size() > 2) {
			sb.append("/search");
		}

        RestResponse response = getClient().request(sb.toString(), GET, parameters);
        Map<String, Object> data = response.toMap();
		page = getIntValue(data.get("page"));
		limit = getIntValue(data.get("limit"));
		pageCount = getIntValue(data.get("pageCount"));
		pageData = new ArrayList<T>();
        
		List<Object> resources = (List<Object>) data.get("resources");
        for (Object resource : resources) {
            if (resource instanceof Map) {
                T instance = makeListItem(getClient(), (Map<String, Object>) resource);
                pageData.add(instance);
            }
        }
	}
    
	/**
	 * Fetch next page
	 *
     * @throws RestException exception
	 */
	protected void fetchNextPage() throws RestException {
	    page++;
	    getListContent();
	}

	/**
	 * Get page data
	 *
	 * @return Page data
	 * @throws RestException exception
	 */
	public List<T> getPageData() throws RestException {
		if (pageData == null) {
			getListContent();
		}

		return Collections.unmodifiableList(pageData);
	}

	/**
	 * Create new object of type T
	 *
	 * @param client the client
	 * @param params the params
	 * @return Oblect item of type T
	 */
	protected abstract T makeListItem(C client, Map<String, Object> params);	
}
