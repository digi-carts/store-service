package com.digicart.store.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * JPA entity mapped in this service schema (Store).
 */
@Entity
@Table(name = "stores", schema = "store_svc")
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "admin_id", nullable = false, unique = true)
    private String adminId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subdomain", nullable = false, unique = true)
    private String subdomain;

    @Column(name = "store_url_id", unique = true)
    private String storeUrlId;

    @Column(name = "domain")
    private String domain;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "currency", nullable = false)
    private String currency = "INR";

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "live", nullable = false)
    private Boolean live = false;

    @Column(name = "visit_count", nullable = false)
    private Integer visitCount = 0;

    @Column(name = "available_days", nullable = false)
    private Integer availableDays = 0;

    @Column(name = "template", nullable = false)
    private String template = "default";

    @Column(name = "branding", columnDefinition = "jsonb", nullable = false)
    private String branding = "{}";

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Creates a new {@code Store}.
     */
    public Store() {}
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
     * Returns admin id.
     * @return the string
     */
    public String getAdminId() { return adminId; }
    /**
     * Sets admin id.
     *
     * @param adminId admin user identifier
     */
    public void setAdminId(String adminId) { this.adminId = adminId; }
    /**
     * Returns name.
     * @return the string
     */
    public String getName() { return name; }
    /**
     * Sets name.
     *
     * @param name name
     */
    public void setName(String name) { this.name = name; }
    /**
     * Returns subdomain.
     * @return the string
     */
    public String getSubdomain() { return subdomain; }
    /**
     * Sets subdomain.
     *
     * @param subdomain store subdomain
     */
    public void setSubdomain(String subdomain) { this.subdomain = subdomain; }
    /**
     * Returns store url id.
     * @return the string
     */
    public String getStoreUrlId() { return storeUrlId; }
    /**
     * Sets store url id.
     *
     * @param storeUrlId store url id
     */
    public void setStoreUrlId(String storeUrlId) { this.storeUrlId = storeUrlId; }
    /**
     * Returns domain.
     * @return the string
     */
    public String getDomain() { return domain; }
    /**
     * Sets domain.
     *
     * @param domain domain
     */
    public void setDomain(String domain) { this.domain = domain; }
    /**
     * Returns email.
     * @return the string
     */
    public String getEmail() { return email; }
    /**
     * Sets email.
     *
     * @param email email address
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Returns phone.
     * @return the string
     */
    public String getPhone() { return phone; }
    /**
     * Sets phone.
     *
     * @param phone phone
     */
    public void setPhone(String phone) { this.phone = phone; }
    /**
     * Returns currency.
     * @return the string
     */
    public String getCurrency() { return currency; }
    /**
     * Sets currency.
     *
     * @param currency currency
     */
    public void setCurrency(String currency) { this.currency = currency; }
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
     * Returns live.
     * @return the boolean
     */
    public Boolean getLive() { return live; }
    /**
     * Sets live.
     *
     * @param live live
     */
    public void setLive(Boolean live) { this.live = live; }
    /**
     * Returns visit count.
     * @return the integer
     */
    public Integer getVisitCount() { return visitCount; }
    /**
     * Sets visit count.
     *
     * @param visitCount visit count
     */
    public void setVisitCount(Integer visitCount) { this.visitCount = visitCount; }
    /**
     * Returns available days.
     * @return the integer
     */
    public Integer getAvailableDays() { return availableDays; }
    /**
     * Sets available days.
     *
     * @param availableDays available days
     */
    public void setAvailableDays(Integer availableDays) { this.availableDays = availableDays; }
    /**
     * Returns template.
     * @return the string
     */
    public String getTemplate() { return template; }
    /**
     * Sets template.
     *
     * @param template template
     */
    public void setTemplate(String template) { this.template = template; }
    /**
     * Returns branding.
     * @return the string
     */
    public String getBranding() { return branding; }
    /**
     * Sets branding.
     *
     * @param branding branding
     */
    public void setBranding(String branding) { this.branding = branding; }
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
