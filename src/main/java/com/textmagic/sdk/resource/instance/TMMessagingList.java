package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import static com.textmagic.sdk.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class TMMessagingList<T extends Resource, C extends RestClient> extends Resource<C> {
    
    /**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 */
	public TMMessagingList(final C client) {
		this(client, new HashMap<String, String>());
	}

	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 * @param parameters Resource parameters
	 */
	public TMMessagingList(final C client, Map<String, String> parameters) {
		super(client);
		this.parameters = parameters;
	}

    @Override
    protected String getResourcePath() {
        return "stats/messaging";
    }

    /**
     * Retrieve messaging list
     *
     * @return Messaging list
     * @throws RestException exception
     */
    @SuppressWarnings("unchecked")
	public Iterator<TMMessaging> iterator() throws RestException {
    	List<TMMessaging> data = new ArrayList<TMMessaging>();

        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        List<Object> items = response.toList();
        
        for (Object item : items) {
            if (item instanceof Map) {
                data.add(new TMMessaging(getClient(), (Map<String, Object>) item));
            }
        }
    	
    	return Collections.unmodifiableList(data).iterator();
    }
}
