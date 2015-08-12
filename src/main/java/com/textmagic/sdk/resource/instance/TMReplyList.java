package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMReplyList extends ListResource<TMReply, RestClient> {

    /**
     * Instantiates reply list
     *
     * @param client HTTP client
     */
	public TMReplyList(RestClient client) {
		super(client);
	}
    
    /**
     * Instantiates reply list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMReplyList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    protected String getResourcePath() {
        return "replies";
    } 

	@Override
	protected TMReply makeListItem(RestClient client, Map<String, Object> params) {
		return new TMReply(client, params);
	}
}
