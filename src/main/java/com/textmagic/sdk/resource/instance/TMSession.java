package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMSession extends InstanceResource<RestClient> {

    /**
     * Instantiates session
     *
     * @param client HTTP client
     */
    public TMSession(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates session
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMSession(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "sessions";
    } 
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for session");
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
     * Retrieve startTime
     *
     * @return startTime
     */
    public Date getStartTime() {
        return getDate("startTime");
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
     * Retrieve source
     *
     * @return source
     */
    public String getSource() {
        return (String) getProperty("source");
    }
    
    /**
     * Retrieve referenceId
     *
     * @return referenceId
     */
    public String getReferenceId() {
        return (String) getProperty("referenceId");
    }
    
    /**
     * Retrieve price
     *
     * @return price
     */
    public Double getPrice() {
        Object value = getProperty("price");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("price");
    }
    
    /**
     * Retrieve numbersCount
     *
     * @return numbersCount
     */
    public Integer getNumbersCount() {
        return (Integer) getProperty("numbersCount");
    }
}
