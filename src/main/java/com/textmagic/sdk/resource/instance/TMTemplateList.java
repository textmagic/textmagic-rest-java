package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMTemplateList extends ListResource<TMTemplate, RestClient> {

    /**
     * Instantiates template list
     *
     * @param client HTTP client
     */
	public TMTemplateList(RestClient client) {
		super(client);
	}

    /**
     * Instantiates template list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMTemplateList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    protected String getResourcePath() {
        return "templates";
    } 

	@Override
	protected TMTemplate makeListItem(RestClient client, Map<String, Object> params) {
		return new TMTemplate(client, params);
	}
}
