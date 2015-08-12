package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMUnsubscriberList extends ListResource<TMUnsubscriber, RestClient> {

    /**
     * Instantiates unsubscriber list
     *
     * @param client HTTP client
     */
	public TMUnsubscriberList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "unsubscribers";
    } 

	@Override
	protected TMUnsubscriber makeListItem(RestClient client, Map<String, Object> params) {
		return new TMUnsubscriber(client, params);
	}
}
