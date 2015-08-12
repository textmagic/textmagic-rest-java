package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

public class TMSchedule extends InstanceResource<RestClient> {

    /**
     * Instantiates schedule
     *
     * @param client HTTP client
     */
    public TMSchedule(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates schedule
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMSchedule(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "schedules";
    } 
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for schedule");
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
     * Retrieve nextSend
     *
     * @return nextSend
     */
    public Date getNextSend() {
        return getDate("nextSend");
    }
    
    /**
     * Retrieve rrule
     *
     * @return rrule
     */
    public String getRrule() {
        return (String) getProperty("rrule");
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
