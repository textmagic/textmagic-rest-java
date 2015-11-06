package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import static com.textmagic.sdk.RequestMethod.GET;

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
     *
     * @return Dedicated numbers
     * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
     */
    @SuppressWarnings("unchecked")
	public List<String> getDedicatedNumbers() throws RestException, ClientException {
        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("dedicated");
    }
    
    /**
     * Retrieve user phones
     *
     * @return User phones
     * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
     */
    @SuppressWarnings("unchecked")
	public List<String> getUserPhones() throws RestException, ClientException {
        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("user");
    }
    
    /**
     * Retrieve shared numbers
     *
     * @return Shared numbers
     * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
     */
    @SuppressWarnings("unchecked")
	public List<String> getSharedNumbers() throws RestException, ClientException {
        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("shared");
    }
    
    /**
     * Retrieve numbers
     *
     * @return numbers
     * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
     */
    @SuppressWarnings("unchecked")
	public List<String> getSenderIds() throws RestException, ClientException {
        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        Map<String, Object> sources = new HashMap<String, Object>(response.toMap());

    	return (List<String>) sources.get("senderIds");
    }
}
