package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import static com.textmagic.sdk.RequestMethod.POST;
import static com.textmagic.sdk.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
        		new String[] {"text", "templateId", "sendingTime", "contacts", "lists", "phones", "cutExtra", "partsCount", "referenceId", "from", "rrule"}
        	)
        );
    }
    
    @Override
    protected String getResourcePath() {
        return "messages";
    }

    protected String getPriceResourcePath() {
        return "messages/price";
    }

	/**
	 * Send new message
	 *
     * @return Error state
	 * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
	 */
	public boolean send() throws RestException, ClientException {
		RestResponse response = getClient().request(getResourcePath(), POST, buildRequestParameters(properties));
        this.properties = new HashMap<String, Object>(response.toMap());
        return !response.isError();
	}
    
	/**
	 * Get message price
	 *
	 * @return Message price
	 * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
	 */
	public Double getPrice() throws RestException, ClientException {
		RestResponse response = getClient().request(getPriceResourcePath(), GET, buildRequestParameters(properties));
		Map<String, Object> result = new HashMap<String, Object>(response.toMap());
		// Return correct type even if object is unmarshalled to Integer
		Object total = result.get("total");
		if (total instanceof Integer) {
			return ((Integer) total).doubleValue();
		}
		return (Double) total;
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
	 * @param name Property name
	 * @param value Property value
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
     *
     * @param text Text
     */
    public void setText(String text) {
        setProperty("text", text);
    }
    
    /**
     * Set templateId
     *
     * @param templateId Template id
     */
    public void setTemplateId(Integer templateId) {
        setProperty("templateId", templateId);
    }
    
    /**
     * Set sendingTime
     *
     * @param sendingTime Sending time
     */
    public void setSendingTime(Date sendingTime) {
        long timestamp = (sendingTime.getTime() / 1000);
        setProperty("sendingTime", String.valueOf(timestamp));
    }
    
    /**
     * Set contacts
     *
     * @param contacts Contacts
     */
    public void setContacts(final List<Integer> contacts) {
        List<String> param = new ArrayList<String>();
		for (Integer id : contacts) {
			param.add(Integer.toString(id));
		}
    	
    	setProperty("contacts", StringUtils.join(param, ","));
    }
    
    /**
     * Set lists
     *
     * @param lists Lists
     */
    public void setLists(final List<Integer> lists) {
        List<String> param = new ArrayList<String>();
		for (Integer id : lists) {
			param.add(Integer.toString(id));
		}
    	
    	setProperty("lists", StringUtils.join(param, ","));
    }
    
    /**
     * Set phones
     *
     * @param phones Phones
     */
    public void setPhones(final List<String> phones) {
        setProperty("phones", StringUtils.join(phones, ","));
    }
    
    /**
     * Set cutExtra
     *
     * @param cutExtra Extra cut
     */
    public void setCutExtra(Boolean cutExtra) {
        setProperty("cutExtra", cutExtra);
    }
    
    /**
     * Set partsCount
     *
     * @param partsCount Parts count
     */
    public void setPartsCount(Integer partsCount) {
        setProperty("partsCount", partsCount);
    }
    
    /**
     * Set referenceId
     *
     * @param referenceId Reference id
     */
    public void setReferenceId(String referenceId) {
        setProperty("referenceId", referenceId);
    }
    
    /**
     * Set from
     *
     * @param from From
     */
    public void setFrom(String from) {
        setProperty("from", from);
    }
    
    /**
     * Set rrule
     *
     * @param rrule Rrule
     */
    public void setRrule(String rrule) {
        setProperty("rrule", rrule);
    }
}
