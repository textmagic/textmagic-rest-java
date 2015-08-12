package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Map;

public class TMUser extends InstanceResource<RestClient> {

    /**
     * Instantiates user
     *
     * @param client HTTP client
     */
    public TMUser(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates user
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMUser(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return null;
    }
    
    @Override
	public boolean get(Integer id) throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for user");
	}
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for user");
	}
    
    @Override
	public boolean delete() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for user");
	}
    
    /**
     * Retrieve id
     *
     * @return id
     */
    public Integer getId() {
        return (Integer) getProperty("id");
    }
    
    /**
     * Retrieve username
     *
     * @return username
     */
    public String getUsername() {
        return (String) getProperty("username");
    }
    
    /**
     * Retrieve firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return (String) getProperty("firstName");
    }
    
    /**
     * Retrieve lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return (String) getProperty("lastName");
    }
    
    /**
     * Retrieve company
     *
     * @return company
     */
    public String getCompany() {
        return (String) getProperty("company");
    }

    /**
     * Retrieve status
     *
     * @return status
     */
    public String getStatus() {
        return (String) getProperty("status");
    }
    
    /**
     * Retrieve balance
     *
     * @return balance
     */
    public Double getBalance() {
        Object value = getProperty("balance");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("balance");
    }
    
    /**
     * Retrieve subaccountType
     *
     * @return subaccountType
     */
    public String getSubaccountType() {
        return (String) getProperty("subaccountType");
    }
    
    /**
     * Retrieve currency
     *
     * @return currency
     */
    @SuppressWarnings("unchecked")
	public String getCurrency() {
        Map<String, Object> currency = (Map<String, Object>) getProperty("currency");
        
        return (String) currency.get("id");
    }
}
