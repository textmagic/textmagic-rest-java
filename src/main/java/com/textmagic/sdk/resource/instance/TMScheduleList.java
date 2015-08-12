package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.resource.ListResource;

import java.util.Map;

public class TMScheduleList extends ListResource<TMSchedule, RestClient> {

    /**
     * Instantiates schedule list
     *
     * @param client HTTP client
     */
	public TMScheduleList(RestClient client) {
		super(client);
	}

    @Override
    protected String getResourcePath() {
        return "schedules";
    } 

	@Override
	protected TMSchedule makeListItem(RestClient client, Map<String, Object> params) {
		return new TMSchedule(client, params);
	}
}
