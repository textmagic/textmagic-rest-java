package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.InstanceResource;

import static com.textmagic.sdk.RequestMethod.POST;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashSet;

public class TMUnsubscriber extends InstanceResource<RestClient> {

    /**
     * Instantiates unsubscriber
     *
     * @param client HTTP client
     */
    public TMUnsubscriber(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"phone"}
        	)
        );
    }

    /**
     * Instantiates unsubscriber
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMUnsubscriber(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"phone"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "unsubscribers";
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
			throw new UnsupportedOperationException("This operation is unsupported for unsubscriber");
		}
	}
	
    @Override
	public boolean delete() {
		throw new UnsupportedOperationException("This operation is unsupported for unsubscriber");
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
     * Set phone
     *
     * @param phone Phone
     */
    public void setPhone(String phone) {
        setProperty("phone", phone);
    }
    
    /**
     * Retrieve unsubscribeTime
     *
     * @return unsubscribeTime
     */
    public Date getUnsubscribeTime() {
        return getDate("unsubscribeTime");
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
