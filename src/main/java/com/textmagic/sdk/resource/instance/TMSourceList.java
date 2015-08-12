package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class TMSourceList<T extends Resource, C extends RestClient> extends Resource<C> {
    
    /**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 */
	public TMSourceList(final C client) {
		this(client, new HashMap<String, String>());
	}

	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 * @param parameters Resource parameters
	 */
	public TMSourceList(final C client, Map<String, String> parameters) {
		super(client);
		this.parameters = parameters;
	}

    @Override
    protected String getResourcePath() {
        return "sources";
    }

    /**
     * Retrieve dedicated numbers
     * @throws RestException 
     */
    @SuppressWarnings("unchecked")
	public List<String> getDedicatedNumbers() throws RestException {
        RestResponse response = getClient().request(getResourcePath(), "GET", parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("dedicated");
    }
    
    /**
     * Retrieve user phones
     * @throws RestException 
     */
    @SuppressWarnings("unchecked")
	public List<String> getUserPhones() throws RestException {
        RestResponse response = getClient().request(getResourcePath(), "GET", parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("user");
    }
    
    /**
     * Retrieve shared numbers
     * @throws RestException 
     */
    @SuppressWarnings("unchecked")
	public List<String> getSharedNumbers() throws RestException {
        RestResponse response = getClient().request(getResourcePath(), "GET", parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("shared");
    }
    
    /**
     * Retrieve numbers
     * @throws RestException 
     */
    @SuppressWarnings("unchecked")
	public List<String> getSenderIds() throws RestException {
        RestResponse response = getClient().request(getResourcePath(), "GET", parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("senderIds");
    }
}
