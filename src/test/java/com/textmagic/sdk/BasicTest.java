package com.textmagic.sdk;

import com.textmagic.sdk.RestClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.hamcrest.Description;
import org.junit.Before;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class ListNameValuePairMatcher extends ArgumentMatcher<List<NameValuePair>> {
	 
    private List<NameValuePair> expected;
    private List<NameValuePair> actual;
 
    public ListNameValuePairMatcher(List<NameValuePair> expected) {
        this.expected = expected;
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public boolean matches(Object actual) {
    	this.actual = (List<NameValuePair>) actual;
    	
    	Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {
    	    @Override
    	    public int compare(NameValuePair p1, NameValuePair p2) {
    	      return p1.getName().compareTo(p2.getName());
    	    }
    	};
    	
    	Collections.sort(this.expected, comp);
    	Collections.sort(this.actual, comp);
    	return this.expected.equals(this.actual);
    }
 
    @Override
    public void describeTo(Description description) {
        description.appendText(expected == null ? null : expected.toString());
    }
}

public class BasicTest {

	final String username = "<USERNAME>";
	final String token = "<APIV2_TOKEN>";

	protected RestClient client = spy(new RestClient(username, token));

	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	protected void setMockResponse(final RequestMethod method, final String url, final Map<String, String> params, String resourceName, Integer status) throws Exception {
		String response = null;
		if (resourceName != null) {
			response = IOUtils.toString(BasicTest.class.getResourceAsStream(resourceName), "UTF-8");
		}
		List<NameValuePair> paramList = RestClient.buildParametersList(params);
		
		RestResponse result = new RestResponse(response, status);
		when(client.request(Matchers.eq(url), Matchers.eq(method), Matchers.argThat(new ListNameValuePairMatcher(paramList)))).thenReturn(result);
	}
}