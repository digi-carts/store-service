package com.digicart.store.dto;

/**
 * Request/response DTO: Update Store Page Request.
 */
public class UpdateStorePageRequest {

    private String slug;
    private String title;
    private String content;
    private Boolean published;

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
