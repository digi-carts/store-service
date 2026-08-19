package com.digicart.store.dto;

/**
 * Request/response DTO: Update Store Page Request.
 */
public class UpdateStorePageRequest {

    private String slug;
    private String title;
    private String content;
    private Boolean published;

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }
}
