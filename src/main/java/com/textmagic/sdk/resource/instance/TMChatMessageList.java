package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMChatMessageList extends ListResource<TMChatMessage, RestClient> {
    
    /**
     * Phone
     */
    private String phone;
    
    /**
     * Instantiates chat message list
     *
     * @param client HTTP client
     * @param phone Phone
     */
	public TMChatMessageList(RestClient client, String phone) {
		super(client);
        this.phone = phone;
	}

    @Override
    protected String getResourcePath() {
        return "chats/" + phone;
    } 

	@Override
	protected TMChatMessage makeListItem(RestClient client, Map<String, Object> params) {
		return new TMChatMessage(client, params);
	}
}
