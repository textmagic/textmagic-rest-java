package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMCustomFieldList extends ListResource<TMCustomField, RestClient> {

    /**
     * Instantiates custom field list
     *
     * @param client HTTP client
     */
	public TMCustomFieldList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "customfields";
    } 

	@Override
	protected TMCustomField makeListItem(RestClient client, Map<String, Object> params) {
		return new TMCustomField(client, params);
	}
}
