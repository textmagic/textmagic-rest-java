package com.textmagic.sdk.resource;

import com.textmagic.sdk.RestClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class Resource<C extends RestClient> {

    /**
     * Http client instance
     */
	private C client;

    /**
     * Resource parameters
     */
	protected Map<String, String> parameters;
    
    /**
     * POST and PUT request allowed fields
     */
	protected Set<String> requestFields;
    
	/**
	 * Instantiates resource
	 *
	 * @param client HTTP client
	 */
	public Resource(C client) {
		this.client = client;
	}

	/**
	 * Retrieve HTTP Client
	 *
	 * @return HTTP client instance
	 */
	protected C getClient() {
		return this.client;
	}

	/**
	 * Retrieve resource path
	 *
	 * @return Resource path
	 */
	protected abstract String getResourcePath();
    
    /**
	 * Get int value
	 *
	 * @param data the data
	 * @return Int value
	 */
	protected Integer getIntValue(Object data) {
		if (data instanceof Integer) {
			return (Integer) data;
		}
		if (data instanceof String) {
			return Integer.parseInt((String) data);
		}

		return -1;
	}
    
    /**
	 * Get string value
	 *
	 * @param data the data
	 * @return String value
	 */
	protected String getStringValue(Object data) {
		if (data instanceof String) {
            return (String) data;
        } else if (data instanceof Integer) {
            return Integer.toString((Integer) data);
        } else if (data instanceof Boolean) {
        	if ((Boolean) data == true) {
        		return "1";
        	} else {
        		return "0";
        	}
        }

		return null;
	}
    
    /**
	 * Build request parameters list
	 *
	 * @param paramMap Parameters map container
	 * @return Parameters list
	 */
	protected List<NameValuePair> buildRequestParameters(final Map<String, Object> paramMap) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		if (paramMap != null && paramMap.size() > 0) {
			for (final String var : paramMap.keySet()) {
				String paramStr = getStringValue(paramMap.get(var));
                
				if (paramStr != null && requestFields.contains(var)) {
					paramList.add(new BasicNameValuePair(var, paramStr));
				}
			}
		}

		return paramList;
	}
}
