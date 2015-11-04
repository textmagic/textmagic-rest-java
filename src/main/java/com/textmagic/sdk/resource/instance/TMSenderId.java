package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.HashSet;

import static com.textmagic.sdk.RequestMethod.POST;

import java.util.Arrays;
import java.util.Map;

public class TMSenderId extends InstanceResource<RestClient> {

    /**
     * Instantiates sender id
     *
     * @param client HTTP client
     */
    public TMSenderId(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"senderId", "explanation"}
        	)
        );
    }

    /**
     * Instantiates sender id
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMSenderId(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"senderId", "explanation"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "senderids";
    }
    
    @Override
	public boolean createOrUpdate() throws RestException {
		if (getProperty("id") == null) {
            RestResponse response = getClient().request(getResourcePath(), POST, buildRequestParameters(properties));
            Map<String, Object> properties = response.toMap();
            Integer id = (Integer) properties.get("id");
            clearProperties();
            return get(id);
		} else {
			throw new UnsupportedOperationException("This operation is unsupported for sender id");
		}
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
     * Retrieve senderId
     *
     * @return senderId
     */
    public String getSenderId() {
        return (String) getProperty("senderId");
    }
    
    /**
     * Set senderId
     *
     * @param senderId Sender id
     */
    public void setSenderId(String senderId) {
        setProperty("senderId", senderId);
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
     * Retrieve user
     *
     * @return user
     */
    @SuppressWarnings("unchecked")
	public TMUser getUser() {
        return new TMUser(getClient(), (Map<String, Object>) getProperty("user"));
    }
    
    /**
     * Set explanation
     *
     * @param explanation Explanation
     */
    public void setExplanation(String explanation) {
        setProperty("explanation", explanation);
    }   
}
