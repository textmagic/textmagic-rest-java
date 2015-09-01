package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMContactListList extends ListResource<TMList, RestClient> {

    /**
     * Contact id
     */
    private Integer contactId;

    /**
     * Instantiates contact list list
     *
     * @param client HTTP client
     * @param contactId Contact id
     */
	public TMContactListList(RestClient client, Integer contactId) {
		super(client);
        this.contactId = contactId;
	}

    @Override
    protected String getResourcePath() {
        return "contacts/" + contactId + "/lists";
    } 

	@Override
	protected TMList makeListItem(RestClient client, Map<String, Object> params) {
		return new TMList(client, params);
	}
}
