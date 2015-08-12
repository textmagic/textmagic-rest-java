package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMSubaccountList extends ListResource<TMSubaccount, RestClient> {

    /**
     * Instantiates subaccount list
     *
     * @param client HTTP client
     */
	public TMSubaccountList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "subaccounts";
    } 

	@Override
	protected TMSubaccount makeListItem(RestClient client, Map<String, Object> params) {
		return new TMSubaccount(client, params);
	}
}
