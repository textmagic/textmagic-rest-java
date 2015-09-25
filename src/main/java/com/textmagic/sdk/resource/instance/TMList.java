package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.InstanceResource;

import static com.textmagic.sdk.RequestMethod.DELETE;
import static com.textmagic.sdk.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TMList extends InstanceResource<RestClient> {

    /**
     * Instantiates list
     *
     * @param client HTTP client
     */
    public TMList(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name", "shared"}
        	)
        );
    }

    /**
     * Instantiates list
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMList(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name", "shared"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "lists";
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
     * Retrieve name
     *
     * @return name
     */
    public String getName() {
        return (String) getProperty("name");
    }
    
    /**
     * Set name
     *
     * @param name Name
     */
    public void setName(final String name) {
        setProperty("name", name);
    }
    
    /**
     * Retrieve description
     *
     * @return description
     */
    public String getDescription() {
        return (String) getProperty("description");
    }
    
    /**
     * Retrieve members count
     *
     * @return members count
     */
    public Integer getMembersCount() {
        return (Integer) getProperty("membersCount");
    }
    
    /**
     * Retrieve shared
     *
     * @return shared
     */
    public Boolean getShared() {
        return (Boolean) getProperty("shared");
    }    
    
    /**
     * Set shared
     *
     * @param shared Shared
     */
    public void setShared(final Boolean shared) {
        setProperty("shared", shared);
    }
    
    /**
     * Retrieve list contacts iterator
     *
     * @return Contacts
     */
    public Iterator<TMContact> getContactsIterator() {
    	if (properties.size() > 0) {
    		TMListContactList lcl = new TMListContactList(getClient(), (Integer) getProperty("id"));
            return lcl.iterator();
        } else {
			throw new UnsupportedOperationException("This operation is unsupported for non existent list");
		}
    }
    
    /**
	 * Add contacts to list
     *
     * @param contacts Contacts
     * @return Error state
     * @throws RestException exception
	 */
	public boolean addContactsToList(final List<Integer> contacts) throws RestException {
        if (properties.size() > 0) {
            clearParameters();
            List<String> param = new ArrayList<String>();
            for (Integer id : contacts) {
            	param.add(Integer.toString(id));
            }
            parameters.put("contacts", StringUtils.join(param, ","));
            RestResponse response = getClient().request(getResourcePath() + '/' + getProperty("id") + "/contacts", PUT, parameters);
            clearParameters();
            return !response.isError();
        } else {
			throw new UnsupportedOperationException("This operation is unsupported for non existent list");
		}
	}
    
    /**
	 * Remove contacts from list
     *
     * @param contacts Contacts
     * @return Error state
     * @throws RestException exception
	 */
	public boolean removeContactsFromList(final List<Integer> contacts) throws RestException {
        if (properties.size() > 0) {
            clearParameters();
            List<String> param = new ArrayList<String>();
            for (Integer id : contacts) {
            	param.add(Integer.toString(id));
            }
            parameters.put("contacts", StringUtils.join(param, ","));
            RestResponse response = getClient().request(getResourcePath() + '/' + getProperty("id") + "/contacts", DELETE, parameters);
            clearParameters();
            return !response.isError();
        } else {
			throw new UnsupportedOperationException("This operation is unsupported for non existent list");
		}
	}
}
