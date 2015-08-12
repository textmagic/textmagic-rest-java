package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMChatMessage extends InstanceResource<RestClient> {

    /**
     * Instantiates chat message
     *
     * @param client HTTP client
     */
    public TMChatMessage(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates chat message
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMChatMessage(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "chats";
    }
   
    @Override
	public boolean get(Integer id) throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for chat");
	}
   
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for chat");
	}
    
    @Override
	public boolean delete() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for chat");
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
     * Retrieve direction
     *
     * @return direction
     */
    public String getDirection() {
        return (String) getProperty("direction");
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
        return (Date) getDate("messageTime");
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
    
    /**
     * Retrieve status
     *
     * @return status
     */
    public String getStatus() {
        return (String) getProperty("status");
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
}
