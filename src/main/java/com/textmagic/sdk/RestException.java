package com.textmagic.sdk;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.text.StrBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestException extends Exception {

    /**
	 * UID for serializer
	 */
	private static final long serialVersionUID = -617296495882003033L;
	
	private static final String KEY_SEPARATOR = " ";

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
	private Map<String, List<String>> errors;
	
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
	public RestException(String message, Integer code, Map<String, List<String>> errors) {
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
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		
		message = (String) data.get("message");
			
		if (data.get("code") != null) {
			code = (Integer) data.get("code");
		}
		if (data.get("errors") != null) {
			addErrors("", (Map<String, Object>) data.get("errors"), errors);
		}

		return new RestException(message, code, errors);
	}

	/**
	 * Recursively iterates through nested maps, until either List or plain object is encountered.
	 * <p>
	 * If map element's value is a Map, calls self recursively, passing Map's value as restErrors.
	 * The prefix value is constructed by taking the current prefix, adding KEY_SEPARATOR and current key.
	 * </p>
	 * <p>
	 * If map element's value is a List, converts each Lists element to String and puts resulting List of to result map.
	 * </p>
	 * <p>
	 * If map element's value is neither List nor Map, converts the Object to String and adds a single element List to result map.
	 * </p>
	 *
	 * @param prefix is used as a part of key for objects added into result map
	 * @param restErrors Map which is iterated upon
	 * @param result is used to store the generated results
	 */
	@SuppressWarnings("unchecked")
	private static void addErrors(String prefix, Map<String, Object> restErrors, Map<String, List<String>> result) {
		// At this point we don't know what the map contains
		// We assume that keys are Strings, and recursively descend the map checking each level's values for type.
		// If value type is not a Map, we return the result map, which is then used to construct the result on a higher level
		for (Entry<String, Object> entry : restErrors.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			String resultKey = prefix.length() == 0 ? key : prefix + KEY_SEPARATOR + key;
			if (value instanceof Map) {
				addErrors(resultKey, (Map<String, Object>) value, result);
				continue;
			}
			if (value instanceof List) {
				List<String> list = new ArrayList<String>();
				for (Object v : (List<Object>) value) {
					list.add(v.toString());
				}
				result.put(resultKey, list);
				continue;
			}
			// Value is not a list or map - get it's String representation and put it in a list.
			List<String> list = new ArrayList<String>(1);
			String msg = value != null ? value.toString() : "null";
			list.add(msg);
			result.put(resultKey, list);
		}
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
	public Map<String, List<String>> getErrors() {
		return errors;
	}

	/**
	 * Retrieve textual representation of exception data:<br>
	 * <ol>
	 * <li> Error code returned by server<br>
	 * <li> Error message<br>
	 * <li> Error list
	 * </ol>
	 * @return textual representation of Exception data
	 */
	public String getTextRepresentation() {
		StrBuilder sb = new StrBuilder();
		sb.append("RestException[\n\t");
		sb.append(message).append("\n\t");
		for(Entry<String, List<String>> msgs : errors.entrySet()) {
			sb.append(msgs.getKey()).append(" => [");
			sb.appendWithSeparators(msgs.getValue(), "\n\t\t");
			sb.append("]\n\t"); 
		}
		sb.setLength(sb.length() - 1);
		return sb.append("]").toString();
	}
}
