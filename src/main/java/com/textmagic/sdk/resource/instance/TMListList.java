package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMListList extends ListResource<TMList, RestClient> {

    /**
     * Instantiates list list
     *
     * @param client HTTP client
     */
	public TMListList(RestClient client) {
		super(client);
	}

    /**
     * Instantiates list list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMListList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    protected String getResourcePath() {
        return "lists";
    } 

	@Override
	protected TMList makeListItem(RestClient client, Map<String, Object> params) {
		return new TMList(client, params);
	}
}
