package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMMessageList extends ListResource<TMMessage, RestClient> {

    /**
     * Instantiates message list
     *
     * @param client HTTP client
     */
	public TMMessageList(RestClient client) {
		super(client);
	}
    
    /**
     * Instantiates message list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMMessageList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    protected String getResourcePath() {
        return "messages";
    } 

	@Override
	protected TMMessage makeListItem(RestClient client, Map<String, Object> params) {
		return new TMMessage(client, params);
	}
}
