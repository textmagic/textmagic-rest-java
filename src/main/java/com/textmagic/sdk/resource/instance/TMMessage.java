package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMMessage extends InstanceResource<RestClient> {

    /**
     * Instantiates message
     *
     * @param client HTTP client
     */
    public TMMessage(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates message
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMMessage(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "messages";
    } 
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for message");
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
     * Retrieve receiver
     *
     * @return receiver
     */
    public String getReceiver() {
        return (String) getProperty("receiver");
    }
    
    /**
     * Retrieve messageTime
     *
     * @return messageTime
     */
    public Date getMessageTime() {
        return (Date) getDate("messageTime");
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
     * Retrieve text
     *
     * @return text
     */
    public String getText() {
        return (String) getProperty("text");
    }
    
    /**
     * Retrieve charset
     *
     * @return charset
     */
    public String getCharset() {
        return (String) getProperty("charset");
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
     * Retrieve country
     *
     * @return country
     */
    public String getCountry() {
        return (String) getProperty("country");
    }
    
    /**
     * Retrieve sender
     *
     * @return sender
     */
    public String getSender() {
        return (String) getProperty("sender");
    }
    
    /**
     * Retrieve sender
     *
     * @return sender
     */
    public Double getPrice() {
        Object value = getProperty("price");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("price");
    }
    
    /**
     * Retrieve partsCount
     *
     * @return partsCount
     */
    public Integer getPartsCount() {
        return (Integer) getProperty("partsCount");
    }
}
