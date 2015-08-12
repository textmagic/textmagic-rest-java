package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.InstanceResource;

import java.util.Map;

public class TMInvoice extends InstanceResource<RestClient> {

    /**
     * Instantiates invoice
     *
     * @param client HTTP client
     */
    public TMInvoice(final RestClient client) {
        super(client);
    }

    /**
     * Instantiates invoice
     *
     * @param client HTTP client
     * @param properties Resource properties
     */
    public TMInvoice(final RestClient client, final Map<String, Object> properties) {
        super(client, properties);
    } 
    
    @Override
    protected String getResourcePath() {
        return "invoices";
    } 
    
    @Override
	public boolean get(Integer id) throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for invoice");
	}    
    
    @Override
	public boolean createOrUpdate() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for invoice");
	}
    
    @Override
	public boolean delete() throws RestException {
    	throw new UnsupportedOperationException("This operation is unsupported for invoice");
	}    
    
    /**
     * Retrieve id
     *
     * @return id
     */
    public Integer getId() {
        return (Integer) getProperty("id");
    }
    
    /**
     * Retrieve bundle
     *
     * @return bundle
     */
    public Integer getBundle() {
        return (Integer) getProperty("bundle");
    }
    
    /**
     * Retrieve currency
     *
     * @return currency
     */
    public String getCurrency() {
        return (String) getProperty("currency");
    }
    
    /**
     * Retrieve vat
     *
     * @return vat
     */
    public Double getVat() {
    	Object value = getProperty("vat");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("vat");
    }

    /**
     * Retrieve paymentMethod
     *
     * @return paymentMethod
     */
    public String getPaymentMethod() {
        return (String) getProperty("paymentMethod");
    }
}
