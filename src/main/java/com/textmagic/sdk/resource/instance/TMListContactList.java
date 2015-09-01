package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMListContactList extends ListResource<TMContact, RestClient> {

    /**
     * List id
     */
    private Integer listId;

    /**
     * Instantiates list contact list
     *
     * @param client HTTP client
     * @param listId List id
     */
	public TMListContactList(RestClient client, Integer listId) {
		super(client);
        this.listId = listId;
	}

    @Override
    protected String getResourcePath() {
        return "lists/" + listId + "/contacts";
    } 

	@Override
	protected TMContact makeListItem(RestClient client, Map<String, Object> params) {
		return new TMContact(client, params);
	}
}
