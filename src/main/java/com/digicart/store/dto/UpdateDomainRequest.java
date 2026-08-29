package com.digicart.store.dto;

/**
 * Request DTO for updating a store's custom domain fields.
 */
public class UpdateDomainRequest {
    private String domain;
    private String storeUrlId;

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public String getStoreUrlId() { return storeUrlId; }
    public void setStoreUrlId(String storeUrlId) { this.storeUrlId = storeUrlId; }
}
