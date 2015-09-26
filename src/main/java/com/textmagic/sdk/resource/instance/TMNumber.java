package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;

import static com.textmagic.sdk.RequestMethod.GET;
import static com.textmagic.sdk.RequestMethod.POST;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TMNumber extends InstanceResource<RestClient> {

    /**
     * Instantiates number
     *
     * @param client HTTP client
     */
    public TMNumber(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"phone", "country", "userId"}
        	)
        );
    }

    /**
     * Instantiates number
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMNumber(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"phone", "country", "userId"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "numbers";
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
			throw new UnsupportedOperationException("This operation is unsupported for number");
		}
	}
    
    /**
     * Retrieve available numbers
     *
     * @param country Country
     * @param prefix Prefix
     * @return Available numbers
     * @throws RestException exception when TextMagic REST API returns an error
	 * @throws ClientException when error occurs on client side
     */
    @SuppressWarnings("unchecked")
	public List<String> getAvailableNumbers(String country, String prefix) throws RestException, ClientException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("country", country);
        params.put("prefix", prefix);
        RestResponse response = getClient().request(getResourcePath() + "/available", GET, params);
        Map<String, Object> available = new HashMap<String, Object>(response.toMap());

    	return (List<String>) available.get("numbers");
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
     * Retrieve purchasedAt
     *
     * @return purchasedAt
     */
    public Date getPurchasedAt() {
        return getDate("purchasedAt");
    }
    
    /**
     * Retrieve expireAt
     *
     * @return expireAt
     */
    public Date getExpireAt() {
        return getDate("expireAt");
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
     * Retrieve status
     *
     * @return status
     */
    public String getStatus() {
        return (String) getProperty("status");
    }    
    
    /**
     * Retrieve country
     *
     * @return country
     */
    @SuppressWarnings("unchecked")
	public String getCountry() {
        Map<String, Object> country = (Map<String, Object>) getProperty("country");
        
        return (String) country.get("id");
    }
    
    /**
     * Set country
     *
     * @param country Country
     */
    public void setCountry(String country) {
        setProperty("country", country);
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
     * Set userId
     *
     * @param userId User id
     */
    public void setUserId(Integer userId) {
        setProperty("userId", userId);
    }
}
