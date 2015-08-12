package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMContactList extends ListResource<TMContact, RestClient> {

    /**
     * Instantiates contact list
     *
     * @param client HTTP client
     */
	public TMContactList(RestClient client) {
		super(client);
	}

    /**
     * Instantiates contact list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMContactList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    protected String getResourcePath() {
        return "contacts";
    } 

	@Override
	protected TMContact makeListItem(RestClient client, Map<String, Object> params) {
		return new TMContact(client, params);
	}
}
