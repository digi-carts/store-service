package com.digicart.store.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request/response DTO: Create Store Request.
 */
public class CreateStoreRequest {

    @NotBlank
    private String adminId;

    @NotBlank
    private String name;

    @NotBlank
    private String subdomain;

    private String storeUrlId;
    private String domain;
    private String email;
    private String phone;
    private String currency = "INR";
    private Boolean published = false;
    private Boolean live = false;
    private String template = "default";
    private String branding = "{}";

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
}
