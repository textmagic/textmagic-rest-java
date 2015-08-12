package com.textmagic.sdk;

import java.util.Map;
import java.util.ArrayList;

public class RestException extends Exception {

    /**
	 * UID for serializer
	 */
	private static final long serialVersionUID = -617296495882003033L;

	/**
     * Error code
     */
	private Integer code;
	
    /**
     * Error message
     */
	private String message;

    /**
     * Fields errors
     */
	private Map<String, ArrayList<String>> errors;
	
	/**
	 * Instantiates rest exception
	 *
	 * @param message Error message
	 * @param code Error code
	 */
	public RestException(String message, Integer code) {
		this(message, code, null);
	}

	/**
	 * Instantiates a new twilio rest exception.
	 *
	 * @param message Error message
	 * @param code Error code
	 * @param errors Fields errors
	 */
	public RestException(String message, Integer code, Map<String, ArrayList<String>> errors) {
		super(message);

		this.message = message;
		this.code = code;
		this.errors = errors;
	}

	/**
	 * Parse rest response
	 *
	 * @param response Rest response
	 * @return Rest exception
	 */
	@SuppressWarnings("unchecked")
	public static RestException parseResponse(RestResponse response) {
		Map<String, Object> data = response.toMap();
		Integer code = 0;
		String message = "";
		Map<String, ArrayList<String>> errors = null;
		
		message = (String) data.get("message");
			
		if (data.get("code") != null) {
			code = (Integer) data.get("code");
		}
		if (data.get("errors") != null) {
			errors = (Map<String, ArrayList<String>>) data.get("errors");
		}

		return new RestException(message, code, errors);
	}

	/**
	 * Retrieve error code
	 *
	 * @return Error code
	 */
	public Integer getErrorCode() {
		return code;
	}

	/**
	 * Retrieve error message
	 *
	 * @return Error message
	 */
	public String getErrorMessage() {
		return message;
	}
	
	/**
	 * Retrieve fields errors
	 *
	 * @return Fields errors
	 */
	public Map<String, ArrayList<String>> getErrors() {
		return errors;
	}
}
