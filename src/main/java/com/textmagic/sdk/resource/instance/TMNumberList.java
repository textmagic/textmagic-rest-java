package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMNumberList extends ListResource<TMNumber, RestClient> {

    /**
     * Instantiates number list
     *
     * @param client HTTP client
     */
	public TMNumberList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "numbers";
    } 

	@Override
	protected TMNumber makeListItem(RestClient client, Map<String, Object> params) {
		return new TMNumber(client, params);
	}
}
