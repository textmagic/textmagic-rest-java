package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashSet;

public class TMTemplate extends InstanceResource<RestClient> {

    /**
     * Instantiates template
     *
     * @param client HTTP client
     */
    public TMTemplate(final RestClient client) {
        super(client);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name", "content"}
        	)
        );
    }

    /**
     * Instantiates template
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMTemplate(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
        
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"name", "content"}
        	)
        );
    } 
    
    @Override
    protected String getResourcePath() {
        return "templates";
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
     * Retrieve content
     *
     * @return content
     */
    public String getContent() {
        return (String) getProperty("content");
    }
    
    /**
     * Set content
     *
     * @param content Content
     */
    public void setContent(final String content) {
        setProperty("content", content);
    }      
    
    /**
     * Retrieve lastModified
     *
     * @return lastModified
     */
    public Date getLastModified() {
        return getDate("lastModified");
    }
}
