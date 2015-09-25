package com.textmagic.sdk;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.textmagic.sdk.resource.Resource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HttpDeleteEntity extends HttpPost {
    public HttpDeleteEntity(URI url) {
    	super(url);
    }
    @Override
    public String getMethod() {
    	return "DELETE";
    }
}

public class RestClient {

    /**
     * Used version
     */
	private static final String version = "v2";

    /**
     * Username
     */
	private final String username;

    /**
     * Token
     */
	private final String token;

    /**
     * Http client instance
     */
	private HttpClient client;

    /**
     * Previous request time for prevent limit exceed error
     */    
    protected long previousRequestTime = 0;	
	
	/**
	 * Retrieve API URI
	 *
	 * @return API URI
	 */
	public String getApiUri() {
		return "https://api.textmagictesting.com/api/" + version;
	}
    
	/**
	 * Retrieve HTTP Client
	 *
	 * @return HTTP client instance
	 */
    public HttpClient getHttpClient() {
		return client;
	}
    
	/**
	 * Set HTTP Client instance
	 *
	 * @param client HTTP client
	 */
	public void setHttpClient(final HttpClient client) {
		this.client = client;
	}
    
    /**
	 * Instantiates REST client
	 *
	 * @param username API username
	 * @param token API token
	 */
	public RestClient(final String username, final String token) {
		this.username = username;
		this.token = token;

		setHttpClient(new DefaultHttpClient());
		client.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		client.getParams().setParameter("http.socket.timeout", 5000);
		client.getParams().setParameter("http.connection.timeout", 5000);
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
	}

	/**
	 * Retrieve resource object
	 *
	 * @param clazz Class to instantiate
	 * @return Resource object
	 */
	public <T extends Resource<RestClient>> T getResource(Class<T> clazz) {
		T resource = null;
		try {
			resource = clazz
				.getConstructor(RestClient.class)
				.newInstance(this);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return resource;
	}	
	
	/**
	 * Retrieve resource object
	 *
	 * @param clazz Class to instantiate
	 * @param parameters Parameters
	 * @return Resource object
	 */
	public <T extends Resource<RestClient>> T getResource(Class<T> clazz, Map<String, String> parameters) {
		T resource = null;
		try {
			resource = clazz
				.getConstructor(RestClient.class, Map.class)
				.newInstance(this, parameters);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return resource;
	}		
	
	/**
	 * Build get request
	 *
	 * @param path Request path
	 * @param paramList Request params
	 * @return HTTP get instance
	 */
	private HttpGet buildGetRequest(final String path, final List<NameValuePair> paramList) {
		URI uri = buildUri(path, paramList);
        
		return new HttpGet(uri);
	}

	/**
	 * Build post request
	 *
	 * @param path Request path
	 * @param paramList Request params
	 * @return HTTP post instance
	 */
	private HttpPost buildPostRequest(final String path, final List<NameValuePair> paramList) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildFormEntity(paramList);

		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);

		return post;
	}

	/**
	 * Build put request
	 *
	 * @param path Request path
	 * @param paramList Request params
	 * @return HTTP put instance
	 */
	private HttpPut buildPutRequest(final String path, final List<NameValuePair> paramList) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildFormEntity(paramList);

		HttpPut put = new HttpPut(uri);
		put.setEntity(entity);

		return put;
	}

	/**
	 * Build delete request
	 *
	 * @param path Request path
	 * @param paramList Request params
	 * @return HTTP delete instance
	 */
	private HttpDeleteEntity buildDeleteRequest(final String path, final List<NameValuePair> paramList) {
		URI uri = buildUri(path);
		
        UrlEncodedFormEntity entity = buildFormEntity(paramList);

        HttpDeleteEntity delete = new HttpDeleteEntity(uri);
		delete.setEntity(entity);

		return delete;
	}

	/**
	 * Build form entity
	 *
	 * @param paramList Params list
	 * @return Form entity instance
	 */
	private UrlEncodedFormEntity buildFormEntity(final List<NameValuePair> paramList) {
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(paramList, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return entity;
	}

	/**
	 * Build URI
	 *
	 * @param path Query path
	 * @return URI instance
	 */
	private URI buildUri(final String path) {
		return buildUri(path, null);
	}

	/**
	 * Build URI
	 *
	 * @param path Query path
	 * @param paramList Query params
	 * @return URI instance
	 */
	private URI buildUri(final String path, final List<NameValuePair> paramList) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);

		if (paramList != null && paramList.size() > 0) {
			sb.append("?");
			sb.append(URLEncodedUtils.format(paramList, "UTF-8"));
		}

		URI uri;
		try {
			uri = new URI(sb.toString());
		} catch (final URISyntaxException e) {
			throw new IllegalStateException("Invalid uri", e);
		}

		return uri;
	}

	/**
	 * Build parameters list
	 *
	 * @param paramMap Parameters map container
	 * @return Parameters list
	 */
	public static List<NameValuePair> buildParametersList(final Map<String, String> paramMap) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		if (paramMap != null && paramMap.size() > 0) {
			for (final String var : paramMap.keySet()) {
				paramList.add(new BasicNameValuePair(var, paramMap.get(var)));
			}
		}

		return paramList;
	}

	/**
	 * Make request
	 *
	 * @param path Request path
	 * @param method Request method
	 * @return Textmagic response instance
     * @throws RestException exception
	 */
	public RestResponse request(final String path, final String method) throws RestException {
		Map<String, String> param = new HashMap<String, String>();
		
		return request(path, method, param);
	}	
	
	/**
	 * Make request
	 *
	 * @param path Request path
	 * @param method Request method
	 * @param paramList Request params
	 * @return Textmagic response instance
     * @throws RestException exception
	 */
	public RestResponse request(final String path, final String method, final List<NameValuePair> paramList) throws RestException {
		if (path == null && method == null && paramList == null) {
			return new RestResponse("", 0);
		}
		
		HttpUriRequest request = buildRequest(method, path, paramList);

		HttpResponse response;
		try {
	        long requestTimeDiff = new java.util.Date().getTime() - previousRequestTime;
			if (requestTimeDiff < 500) {
	            try {
					Thread.sleep(500 - requestTimeDiff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
			response = client.execute(request);
			previousRequestTime = new java.util.Date().getTime();
			HttpEntity entity = response.getEntity();

			String responseBody = null;
			if (entity != null) {
				responseBody = EntityUtils.toString(entity);
			}

			StatusLine status = response.getStatusLine();
			Integer statusCode = status.getStatusCode();
            
			RestResponse result = new RestResponse(responseBody, statusCode);
			
	        if (200 <= statusCode && statusCode < 300) {
	        	return result;
	        }
			
			throw RestException.parseResponse(result);
		} catch (final ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (final IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	/**
	 * Make request
	 *
	 * @param path Request path
	 * @param method Request method
	 * @param paramMap Request params
	 * @return Textmagic response instance
     * @throws RestException exception
	 */
	public RestResponse request(final String path, final String method, final Map<String, String> paramMap) throws RestException {
		List<NameValuePair> paramList = buildParametersList(paramMap);
        
		return request(path, method, paramList);
	}	
	
	/**
	 * Build request
	 *
	 * @param method Request method
	 * @param path Request path
	 * @param params Request params
	 * @return HTTP request instance
	 */
	private HttpUriRequest buildRequest(final String method, String path, final List<NameValuePair> params) {
		String normalizedPath = path.toLowerCase();
		StringBuilder sb = new StringBuilder();

        sb.append(getApiUri());
        if (!normalizedPath.startsWith("/")) {
            sb.append("/");
        }
        sb.append(path);

		path = sb.toString();

		HttpUriRequest request;
        
        if (method.equalsIgnoreCase("GET")) {
			request = buildGetRequest(path, params);
		} else if (method.equalsIgnoreCase("POST")) {
			request = buildPostRequest(path, params);
		} else if (method.equalsIgnoreCase("PUT")) {
			request = buildPutRequest(path, params);
		} else if (method.equalsIgnoreCase("DELETE")) {
			request = buildDeleteRequest(path, params);
		} else {
			throw new IllegalArgumentException("Unknown Method: " + method);
		}

		request.addHeader(new BasicHeader("User-Agent", "textmagic-rest-java/" + version));
		request.addHeader(new BasicHeader("Accept", "application/json"));
		request.addHeader(new BasicHeader("Accept-Charset", "utf-8"));
		request.addHeader(new BasicHeader("Accept-Language", "en-US"));
		request.addHeader(new BasicHeader("X-TM-Username", username));
		request.addHeader(new BasicHeader("X-TM-Key", token));

		return request;
	}
}
