package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;

import java.util.HashSet;

import static com.textmagic.sdk.RequestMethod.DELETE;
import static com.textmagic.sdk.RequestMethod.GET;
import static com.textmagic.sdk.RequestMethod.POST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TMSubaccount extends TMUser {

    /**
     * Instantiates subaccount
     *
     * @param client HTTP client
     */
    public TMSubaccount(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"email", "role"}
        	)
        );
    }

    /**
     * Instantiates subaccount
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMSubaccount(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"email", "role"}
        	)
        );
    }
    
    @Override
    protected String getResourcePath() {
        return "subaccounts";
    }
    
    @Override
    public boolean get(Integer id) throws RestException, ClientException {
		if (properties.size() == 0) {
            RestResponse response = getClient().request(getResourcePath() + '/' + id, GET);
            this.properties = new HashMap<String, Object>(response.toMap());
            return !response.isError();
		} else {
			throw new UnsupportedOperationException("This operation is unsupported for existent objects");
		}
	}
    
    @Override
	public boolean createOrUpdate() throws RestException, ClientException {
		if (getProperty("id") == null) {
			RestResponse response = getClient().request(getResourcePath(), POST, buildRequestParameters(properties));
            clearProperties();
            return !response.isError();
		} else {
			throw new UnsupportedOperationException("This operation is unsupported for subaccount");
		}
	}
    
	@Override
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
     * Set email
     *
     * @param email Email
     */
    public void setEmail(final String email) {
        setProperty("email", email);
    }
    
    /**
     * Set role:<ul>
     * <li>A for administrator
     * <li>U for regular user
     * </ul>
     *
     * @param role Role
     */
    public void setRole(final String role) {
        setProperty("role", role);
    }
}
