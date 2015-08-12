package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMReply extends InstanceResource<RestClient> {

    /**
     * Instantiates reply
     *
     * @param client HTTP client
     */
    public TMReply(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates reply
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMReply(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "replies";
    } 
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for reply");
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
     * Retrieve sender
     *
     * @return sender
     */
    public String getSender() {
        return (String) getProperty("sender");
    }
    
    /**
     * Retrieve messageTime
     *
     * @return messageTime
     */
    public Date getMessageTime() {
        return getDate("messageTime");
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
     * Retrieve receiver
     *
     * @return receiver
     */
    public String getReceiver() {
        return (String) getProperty("receiver");
    }
}
