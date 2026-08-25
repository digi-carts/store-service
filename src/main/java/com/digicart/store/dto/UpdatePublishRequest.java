package com.digicart.store.dto;

/**
 * Request DTO for updating a store's published state.
 */
public class UpdatePublishRequest {
    private Boolean published;

    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }
}
