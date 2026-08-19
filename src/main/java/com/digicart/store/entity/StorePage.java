package com.digicart.store.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * JPA entity mapped in this service schema (Store Page).
 */
@Entity
@Table(name = "store_pages", schema = "store_svc")
@EntityListeners(AuditingEntityListener.class)
public class StorePage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "published", nullable = false)
    private Boolean published = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Creates a new {@code StorePage}.
     */
    public StorePage() {}
    /**
     * Returns id.
     * @return the string
     */
    public String getId() { return id; }
    /**
     * Sets id.
     *
     * @param id resource identifier
     */
    public void setId(String id) { this.id = id; }
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
    /**
     * Returns created at.
     * @return the instant
     */
    public Instant getCreatedAt() { return createdAt; }
    /**
     * Sets created at.
     *
     * @param createdAt created at
     */
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    /**
     * Returns updated at.
     * @return the instant
     */
    public Instant getUpdatedAt() { return updatedAt; }
    /**
     * Sets updated at.
     *
     * @param updatedAt updated at
     */
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
