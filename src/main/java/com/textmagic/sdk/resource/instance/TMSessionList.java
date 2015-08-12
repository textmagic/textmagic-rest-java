package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMSessionList extends ListResource<TMSession, RestClient> {

    /**
     * Instantiates session list
     *
     * @param client HTTP client
     */
	public TMSessionList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "sessions";
    } 

	@Override
	protected TMSession makeListItem(RestClient client, Map<String, Object> params) {
		return new TMSession(client, params);
	}
}
