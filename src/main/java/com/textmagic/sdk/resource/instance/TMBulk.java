package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMBulk extends InstanceResource<RestClient> {

    /**
     * Instantiates bulk
     *
     * @param client HTTP client
     */
    public TMBulk(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates bulk
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMBulk(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "bulks";
    } 
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for bulk");
	}
    
    @Override
	public boolean delete() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for bulk");
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
     * Retrieve status
     *
     * @return status
     */
    public String getStatus() {
        return (String) getProperty("status");
    }
    
    /**
     * Retrieve itemsProcessed
     *
     * @return itemsProcessed
     */
    public Integer getItemsProcessed() {
        return (Integer) getProperty("itemsProcessed");
    }
    
    /**
     * Retrieve itemsTotal
     *
     * @return itemsTotal
     */
    public Integer getItemsTotal() {
        return (Integer) getProperty("itemsTotal");
    }
    
    /**
     * Retrieve createdAt
     *
     * @return createdAt
     */
    public Date getCreatedAt() {
        return (Date) getDate("createdAt");
    }
    
    /**
     * Retrieve text
     *
     * @return text
     */
    public String getText() {
        return (String) getProperty("text");
    }
    
    /**
     * Retrieve session
     *
     * @return session
     */
    @SuppressWarnings("unchecked")
	public TMSession getSession() {
        return new TMSession(getClient(), (Map<String, Object>) getProperty("session"));
    }
}
