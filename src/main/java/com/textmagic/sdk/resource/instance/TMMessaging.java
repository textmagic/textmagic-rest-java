package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMMessaging extends InstanceResource<RestClient> {

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     */
    public TMMessaging(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMMessaging(final RestClient client, final Map<String, Object> properties) {
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
     * Retrieve replyRate
     *
     * @return replyRate
     */
    public Double getReplyRate() {
        Object value = getProperty("replyRate");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("replyRate");
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
     * Retrieve deliveryRate
     *
     * @return deliveryRate
     */
    public Double getDeliveryRate() {
        Object value = getProperty("deliveryRate");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("deliveryRate");
    }
    
    /**
     * Retrieve costs
     *
     * @return costs
     */
    public Double getCosts() {
        Object value = getProperty("costs");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("costs");
    }

    /**
     * Retrieve messagesReceived
     *
     * @return messagesReceived
     */
    public Integer getMessagesReceived() {
        return (Integer) getProperty("messagesReceived");
    }
    
    /**
     * Retrieve messagesSentDelivered
     *
     * @return messagesSentDelivered
     */
    public Integer getMessagesSentDelivered() {
        return (Integer) getProperty("messagesSentDelivered");
    }
    
    /**
     * Retrieve messagesSentAccepted
     *
     * @return messagesSentAccepted
     */
    public Integer getMessagesSentAccepted() {
        return (Integer) getProperty("messagesSentAccepted");
    }
    
    /**
     * Retrieve messagesSentBuffered
     *
     * @return messagesSentBuffered
     */
    public Integer getMessagesSentBuffered() {
        return (Integer) getProperty("messagesSentBuffered");
    }
    
    /**
     * Retrieve messagesSentFailed
     *
     * @return messagesSentFailed
     */
    public Integer getMessagesSentFailed() {
        return (Integer) getProperty("messagesSentFailed");
    }
    
    /**
     * Retrieve messagesSentRejected
     *
     * @return messagesSentRejected
     */
    public Integer getMessagesSentRejected() {
        return (Integer) getProperty("messagesSentRejected");
    }
    
    /**
     * Retrieve messagesSentParts
     *
     * @return messagesSentParts
     */
    public Integer getMessagesSentParts() {
        return (Integer) getProperty("messagesSentParts");
    }
}
