package com.textmagic.sdk;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

public class RestResponse {

    /**
     * JSON response
     */
	private String jsonResponse;

    /**
     * HTTP status
     */
	private Integer httpStatus;

    /**
     * Error flag
     */
	private boolean error;

	/**
	 * Instantiates rest response
	 *
	 * @param text JSON response
	 * @param status the status
	 */
	public RestResponse(final String text, final Integer status) {
		jsonResponse = text;
		httpStatus = status;
		error = (status >= 400);
	}

	/**
	 * Retrieve raw JSON response body
	 *
	 * @return JSON response
	 */
	public String getJsonResponse() {
		return jsonResponse;
	}

	/**
	 * Retrieve HTTP status code related to response
	 *
	 * @return HTTP response status
	 */
	public Integer getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Retrieve request error status
	 *
	 * @return Error state
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Retrieve map container from JSON response
	 *
	 * @return Map container with repeated elements as List values, sub-objects as Map values. All other types are String values.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			result = new ObjectMapper().readValue(jsonResponse, HashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
    
	/**
	 * Retrieve list container from JSON response
	 *
	 * @return List container with repeated elements as list values.
	 */
    @SuppressWarnings("unchecked")
	public List<Object> toList() {
		List<Object> result = new ArrayList<Object>();

		try {
			result = new ObjectMapper().readValue(jsonResponse, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}