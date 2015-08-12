package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMStatement extends InstanceResource<RestClient> {

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     */
    public TMStatement(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMStatement(final RestClient client, final Map<String, Object> properties) {
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
     * Retrieve userId
     *
     * @return userId
     */
    public Integer getUserId() {
        return (Integer) getProperty("userId");
    }    
    
    /**
     * Retrieve date
     *
     * @return date
     */
    public Date getDate() {
        return (Date) getDate("date");
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
     * Retrieve delta
     *
     * @return delta
     */
    public Double getDelta() {
        Object value = getProperty("delta");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("delta");
    }    
    
    /**
     * Retrieve type
     *
     * @return type
     */
    public String getType() {
        return (String) getProperty("type");
    }
    
    /**
     * Retrieve value
     *
     * @return value
     */
    public String getValue() {
        return (String) getProperty("value");
    }
    
    /**
     * Retrieve comment
     *
     * @return comment
     */
    public String getComment() {
        return (String) getProperty("comment");
    }
}
