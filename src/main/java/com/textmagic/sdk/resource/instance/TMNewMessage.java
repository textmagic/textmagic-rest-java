package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TMNewMessage extends Resource<RestClient> {

    /**
     * Resource properties
     */
	private Map<String, Object> properties;
	
    /**
     * Instantiates new message
     *
     * @param client HTTP client
     */
    public TMNewMessage(final RestClient client) {
        super(client);
        this.properties = new HashMap<String, Object>();
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"text", "templateId", "sendingTime", "contacts", "lists", "phones", "cutExtra", "partsCount", "referenceId", "from", "rrule", "dummy"}
        	)
        );
    }
    
    @Override
    protected String getResourcePath() {
        return "messages";
    }

	/**
	 * Send new message
	 *
	 * @throws RestException
	 */
	public boolean send() throws RestException {
        properties.put("dummy", false);
		RestResponse response = getClient().request(getResourcePath(), "POST", buildRequestParameters(properties));
        this.properties = new HashMap<String, Object>(response.toMap());
        return !response.isError();
	}
    
	/**
	 * Get message price
	 *
	 * @return Message price
	 * @throws RestException
	 */
	public Double getPrice() throws RestException {
        properties.put("dummy", true);
		RestResponse response = getClient().request(getResourcePath(), "POST", buildRequestParameters(properties));
		Map<String, Object> result = new HashMap<String, Object>(response.toMap());
		return (Double) result.get("total");
	}
	
	/**
	 * Retrieve resource property
	 *
	 * @param name Property name
	 * @return Property value
	 */
	protected Object getProperty(String name) {
		return properties.get(name);
	}
	
    /**
	 * Set resource property
	 *
	 * @param Property name
	 * @param Property value
	 */
	private void setProperty(String name, Object value) {
		properties.put(name, value);
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
     * Retrieve type
     *
     * @return type
     */
    public String getType() {
        return (String) getProperty("type");
    }
	
    /**
     * Set text
     */
    public void setText(String text) {
        setProperty("text", text);
    }
    
    /**
     * Set templateId
     */
    public void setTemplateId(Integer templateId) {
        setProperty("templateId", templateId);
    }
    
    /**
     * Set sendingTime
     */
    public void setSendingTime(Date sendingTime) {
        long timestamp = (sendingTime.getTime() / 1000);
    	setProperty("sendingTime", timestamp);
    }
    
    /**
     * Set contacts
     */
    public void setContacts(final List<Integer> contacts) {
        List<String> param = new ArrayList<String>();
		for (Integer id : contacts) {
			param.add(Integer.toString(id));
		}
    	
    	setProperty("contacts", String.join(",", param));
    }
    
    /**
     * Set lists
     */
    public void setLists(final List<Integer> lists) {
        List<String> param = new ArrayList<String>();
		for (Integer id : lists) {
			param.add(Integer.toString(id));
		}
    	
    	setProperty("lists", String.join(",", param));
    }
    
    /**
     * Set phones
     */
    public void setPhones(final List<String> phones) {
        setProperty("phones", String.join(",", phones));
    }
    
    /**
     * Set cutExtra
     */
    public void setCutExtra(Boolean cutExtra) {
        setProperty("cutExtra", cutExtra);
    }
    
    /**
     * Set partsCount
     */
    public void setPartsCount(Integer partsCount) {
        setProperty("partsCount", partsCount);
    }
    
    /**
     * Set referenceId
     */
    public void setReferenceId(String referenceId) {
        setProperty("referenceId", referenceId);
    }
    
    /**
     * Set from
     */
    public void setFrom(String from) {
        setProperty("from", from);
    }
    
    /**
     * Set rrule
     */
    public void setRrule(String rrule) {
        setProperty("rrule", rrule);
    }
}
