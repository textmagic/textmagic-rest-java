package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class TMChat extends InstanceResource<RestClient> {

    /**
     * Instantiates chat
     *
     * @param client HTTP client
     */
    public TMChat(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates chat
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMChat(final RestClient client, final Map<String, Object> properties) {
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
     * Retrieve phone
     *
     * @return phone
     */
    public String getPhone() {
        return (String) getProperty("phone");
    }
    
    /**
     * Retrieve contact
     *
     * @return contact
     */
    public Integer getContact() {
        return (Integer) getProperty("contact");
    }
    
    /**
     * Retrieve unread
     *
     * @return unread
     */
    public Integer getUnread() {
        return (Integer) getProperty("unread");
    }
    
    /**
     * Retrieve updatedAt
     *
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return (Date) getDate("updatedAt");
    }
    
    /**
     * Retrieve chat messages
     *
     * @return Chat messages
     */
    public Iterator<TMChatMessage> getMessagesIterator() {
    	return new TMChatMessageList(getClient(), getPhone()).iterator();
    }
}
