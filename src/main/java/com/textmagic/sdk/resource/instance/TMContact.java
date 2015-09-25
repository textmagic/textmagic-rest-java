package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TMContact extends InstanceResource<RestClient> {

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     */
    public TMContact(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"firstName", "lastName", "phone", "email", "companyName", "lists"}
        	)
        );
    }

    /**
     * Instantiates contact
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMContact(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"firstName", "lastName", "phone", "email", "companyName", "lists"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "contacts";
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
     * Retrieve first name
     *
     * @return first name
     */
    public String getFirstName() {
        return (String) getProperty("firstName");
    }
    
    /**
     * Set first name
     *
     * @param firstName First name
     */
    public void setFirstName(final String firstName) {
        setProperty("firstName", firstName);
    }
    
    /**
     * Retrieve last name
     *
     * @return last name
     */
    public String getLastName() {
        return (String) getProperty("lastName");
    }
    
    /**
     * Set last name
     *
     * @param lastName Last name
     */
    public void setLastName(final String lastName) {
        setProperty("lastName", lastName);
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
    public void setPhone(final String phone) {
        setProperty("phone", phone);
    }
    
    /**
     * Retrieve email
     *
     * @return email
     */
    public String getEmail() {
        return (String) getProperty("email");
    }
    
    /**
     * Set email
     *
     * @param email Email
     */
    public void setEmail(final String email) {
        setProperty("email", email);
    }
    
    /**
     * Retrieve company name
     *
     * @return company name
     */
    public String getCompanyName() {
        return (String) getProperty("companyName");
    }
    
    /**
     * Set company name
     *
     * @param companyName Company name
     */
    public void setCompanyName(final String companyName) {
        setProperty("companyName", companyName);
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
     * Retrieve contact custom fields
     *
     * @return Contact custom fields
     */
    @SuppressWarnings("unchecked")
	public Iterator<TMCustomField> getCustomFieldsIterator() {
    	List<TMCustomField> data = new ArrayList<TMCustomField>();
    	
		List<Object> items = (List<Object>) getProperty("customFields");
        for (Object item : items) {
            if (item instanceof Map) {
                data.add(new TMCustomField(getClient(), (Map<String, Object>) item));
            }
        }
    	
    	return Collections.unmodifiableList(data).iterator();
    }
    
    /**
     * Retrieve contact lists
     *
     * @return Contact lists
     */
    public Iterator<TMList> getListsIterator() {
    	if (properties.size() > 0) {
    		TMContactListList cll = new TMContactListList(getClient(), (Integer) getProperty("id"));
            return cll.iterator();
        } else {
			throw new UnsupportedOperationException("This operation is unsupported for non existent contact");
		}
    }
}
