package com.digicart.store.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request/response DTO: Create Store Page Request.
 */
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

    /**
     * Returns store id.
     * @return the string
     */
    public String getStoreId() { return storeId; }
    /**
     * Sets store id.
     *
     * @param storeId store (tenant) identifier
     */
    public void setStoreId(String storeId) { this.storeId = storeId; }
    /**
     * Returns slug.
     * @return the string
     */
    public String getSlug() { return slug; }
    /**
     * Sets slug.
     *
     * @param slug page slug
     */
    public void setSlug(String slug) { this.slug = slug; }
    /**
     * Returns title.
     * @return the string
     */
    public String getTitle() { return title; }
    /**
     * Sets title.
     *
     * @param title title
     */
    public void setTitle(String title) { this.title = title; }
    /**
     * Returns content.
     * @return the string
     */
    public String getContent() { return content; }
    /**
     * Sets content.
     *
     * @param content content
     */
    public void setContent(String content) { this.content = content; }
    /**
     * Returns published.
     * @return the boolean
     */
    public Boolean getPublished() { return published; }
    /**
     * Sets published.
     *
     * @param published published
     */
    public void setPublished(Boolean published) { this.published = published; }
}
