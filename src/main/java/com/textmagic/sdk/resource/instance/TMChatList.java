package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMChatList extends ListResource<TMChat, RestClient> {

    /**
     * Instantiates chat list
     *
     * @param client HTTP client
     */
	public TMChatList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "chats";
    } 

	@Override
	protected TMChat makeListItem(RestClient client, Map<String, Object> params) {
		return new TMChat(client, params);
	}
}
