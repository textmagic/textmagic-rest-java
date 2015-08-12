package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMBulkList extends ListResource<TMBulk, RestClient> {

    /**
     * Instantiates bulk list
     *
     * @param client HTTP client
     */
	public TMBulkList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "bulks";
    } 

	@Override
	protected TMBulk makeListItem(RestClient client, Map<String, Object> params) {
		return new TMBulk(client, params);
	}
}
