package com.digicart.store.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateStorePageRequest {

    @NotBlank
    private String storeId;

    @NotBlank
    private String slug;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Boolean published = true;

    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }
}
