package com.textmagic.sdk;

/**
 * Denotes error in the API wrapper code.
 * Generally more serious than RestException.
 * It denotes problems such as failure to create wrapper-specific objects or
 * failure to parse response of the REST service.
 * <p>
 * Note: ClientException is unchecked.
 *</p>
 */
public class ClientException extends RuntimeException {

	/**
	 * UID for serializer
	 */
	private static final long serialVersionUID = -6204810092210949132L;

	public ClientException() {
		super();
	}
	
	public ClientException(Throwable t) {
		super(t);
	}
}
