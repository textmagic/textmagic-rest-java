package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMInvoiceList extends ListResource<TMInvoice, RestClient> {

    /**
     * Instantiates invoice list
     *
     * @param client HTTP client
     */
	public TMInvoiceList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "invoices";
    } 

	@Override
	protected TMInvoice makeListItem(RestClient client, Map<String, Object> params) {
		return new TMInvoice(client, params);
	}
}
