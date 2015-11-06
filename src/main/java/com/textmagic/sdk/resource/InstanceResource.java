package com.textmagic.sdk.resource;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RequestMethod;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import static com.textmagic.sdk.RequestMethod.DELETE;
import static com.textmagic.sdk.RequestMethod.GET;
import static com.textmagic.sdk.RequestMethod.POST;
import static com.textmagic.sdk.RequestMethod.PUT;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class InstanceResource<C extends RestClient> extends Resource<C> {

    /**
     * Resource properties
     */
	protected Map<String, Object> properties;
	
	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 */
	public InstanceResource(final C client) {
		super(client);
		clearProperties();
		clearParameters();
	}

	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 * @param properties Resource properties
	 */
	public InstanceResource(final C client, final Map<String, Object> properties) {
		super(client);
		clearParameters();
		if (properties != null && properties.size() > 0) {
			this.properties = new HashMap<String, Object>(properties);
		} else {
			this.properties = new HashMap<String, Object>();
		}
	}

	/**
	 * Clear properties
	 */
	protected void clearProperties() {
		properties = new HashMap<String, Object>();
	}

	/**
	 * Clear parameters
	 */
	protected void clearParameters() {
		parameters = new HashMap<String, String>();
	}	
	
	/**
	 * Retrieve resource property
	 *
	 * @param name Property name
	 * @return Property value
	 */
	protected Object getProperty(String name) {
		return properties.get(name);
	}

	/**
	 * Set resource property
	 *
	 * @param name Property name
	 * @param value Property value
	 */
	protected void setProperty(String name, Object value) {
		properties.put(name, value);
	}

	/**
	 * Get immutable representation of properties
	 * @return Properties collection
	 */
	protected Map<String, Object> getProperties() {
		return Collections.unmodifiableMap(properties);
	}

	/**
	 * Retrieve resource date property
     *
	 * @param name Property name
	 * @return Property value
	 */
	protected Date getDate(String name) {
        String property = (String) getProperty(name);
        
        if (property == null) {
			return null;
		}
		try {
            return DateUtils.parseDateStrictly(property, new String[] {"yyyy-MM-dd'T'HH:mm:ssZ"});
        } catch (ParseException e) {
			return null;
		}
	}
    
	/**
	 * Set resource date property
	 *
	 * @param name Property name
	 * @param value Property value
	 */
    protected void setDate(String name, Date value) {
        properties.put(name, DateFormatUtils.format(value, "yyyy-MM-dd'T'HH:mm:ssZ"));
    }
    
	/**
	 * Get resource item
	 *
	 * @param id Resource item id
	 * @return Error state
	 * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
	 */
	public boolean get(Integer id) throws RestException, ClientException {
		if (properties.size() == 0) {
            RestResponse response = getClient().request(getResourcePath() + '/' + id, GET);
            this.properties = new HashMap<String, Object>(response.toMap());
            
            return !response.isError();
		} else {
			throw new UnsupportedOperationException("This operation is unsupported for existent objects");
		}
	}
	
	/**
	 * Create or update resource item
     *
     * @return Error state
	 * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
	 */
	public boolean createOrUpdate() throws RestException, ClientException {
		String resourcePath = null;
		RequestMethod method = null;
		
		if (getProperty("id") == null) {
			method = POST;
			resourcePath = getResourcePath();
		} else {
			method = PUT;
			resourcePath = getResourcePath() + '/' + getProperty("id");
		}
		
        RestResponse response = getClient().request(resourcePath, method, buildRequestParameters(properties));
        Map<String, Object> properties = response.toMap();
        Integer id = (Integer) properties.get("id");
        clearProperties();
        return get(id);
	}
	
	/**
	 * Delete resource item
     *
     * @return Error state
	 * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
	 */
	public boolean delete() throws RestException, ClientException {
		if (getProperty("id") == null) {
			throw new UnsupportedOperationException("This operation is unsupported for non existent objects");
		} else {
			RestResponse response = getClient().request(getResourcePath() + '/' + getProperty("id"), DELETE);
            clearProperties();
            
            return !response.isError();
		}
	}

	/**
	 * Objects are equal if property Maps are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof InstanceResource) {
			@SuppressWarnings("unchecked")
			InstanceResource<RestClient> other = (InstanceResource<RestClient>) obj;
			return getProperties().equals(other.getProperties());
		}
		return super.equals(obj);
	}
}
