package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.InstanceResource;

import static com.textmagic.sdk.RequestMethod.PUT;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashSet;

public class TMCustomField extends InstanceResource<RestClient> {

    /**
     * Instantiates custom field
     *
     * @param client HTTP client
     */
    public TMCustomField(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name"}
        	)
        );
    }

    /**
     * Instantiates custom field
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMCustomField(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "customfields";
    } 
    
    /**
	 * Update custom field contact value
     *
     * @param contactId Contact id
     * @param value Value
     * @return Error state
     * @throws RestException exception
	 */
	public boolean updateContactValue(final Integer contactId, final String value) throws RestException {
        if (properties.size() > 0) {
            clearParameters();
            parameters.put("contactId", Integer.toString(contactId));
            parameters.put("value", value);
            RestResponse response = getClient().request(getResourcePath() + '/' + getProperty("id") + "/update", PUT, parameters);
            clearParameters();
            return !response.isError();
        } else {
			throw new UnsupportedOperationException("This operation is unsupported for non existent custom field");
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
     * Retrieve createdAt
     *
     * @return createdAt
     */
    public Date getCreatedAt() {
        return getDate("createdAt");
    }
}
