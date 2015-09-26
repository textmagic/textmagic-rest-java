package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.ListResource;

import static com.textmagic.sdk.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TMStatementList extends ListResource<TMStatement, RestClient> {

    /**
     * Instantiates contact list
     *
     * @param client HTTP client
     */
	public TMStatementList(RestClient client) {
		super(client);
	}

    /**
     * Instantiates contact list
     *
     * @param client HTTP client
     * @param parameters Resource parameters
     */
	public TMStatementList(RestClient client, Map<String, String> parameters) {
		super(client, parameters);
	}

    @Override
    @SuppressWarnings("unchecked")
	protected void getListContent() throws RestException, ClientException {
		parameters.put("page", Integer.toString(this.page));
        parameters.put("limit", Integer.toString(this.limit));

        RestResponse response = getClient().request(getResourcePath(), GET, parameters);
        Map<String, Object> data = response.toMap();
		page = getIntValue(data.get("page"));
		limit = getIntValue(data.get("limit"));
		pageCount = getIntValue(data.get("pageCount"));
		pageData = new ArrayList<TMStatement>();
        
		List<Object> resources = (List<Object>) data.get("resources");
        for (Object resource : resources) {
            if (resource instanceof Map) {
            	TMStatement instance = makeListItem(getClient(), (Map<String, Object>) resource);
                pageData.add(instance);
            }
        }
	}
    
    @Override
    protected String getResourcePath() {
        return "stats/spending";
    } 

	@Override
	protected TMStatement makeListItem(RestClient client, Map<String, Object> params) {
		return new TMStatement(client, params);
	}
}
