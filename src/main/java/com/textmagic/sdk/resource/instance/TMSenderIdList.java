package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMSenderIdList extends ListResource<TMSenderId, RestClient> {

    /**
     * Instantiates sender id list
     *
     * @param client HTTP client
     */
	public TMSenderIdList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "senderids";
    } 

	@Override
	protected TMSenderId makeListItem(RestClient client, Map<String, Object> params) {
		return new TMSenderId(client, params);
	}
}
