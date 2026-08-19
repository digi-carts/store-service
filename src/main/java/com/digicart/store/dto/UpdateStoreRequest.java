package com.digicart.store.dto;

/**
 * Request/response DTO: Update Store Request.
 */
public class UpdateStoreRequest {

    private String name;
    private String subdomain;
    private String storeUrlId;
    private String domain;
    private String email;
    private String phone;
    private String currency;
    private Boolean published;
    private Boolean live;
    private Integer visitCount;
    private Integer availableDays;
    private String template;
    private String branding;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSubdomain() { return subdomain; }
    public void setSubdomain(String subdomain) { this.subdomain = subdomain; }
    public String getStoreUrlId() { return storeUrlId; }
    public void setStoreUrlId(String storeUrlId) { this.storeUrlId = storeUrlId; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }
    public Boolean getLive() { return live; }
    public void setLive(Boolean live) { this.live = live; }
    public Integer getVisitCount() { return visitCount; }
    public void setVisitCount(Integer visitCount) { this.visitCount = visitCount; }
    public Integer getAvailableDays() { return availableDays; }
    public void setAvailableDays(Integer availableDays) { this.availableDays = availableDays; }
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public String getBranding() { return branding; }
    public void setBranding(String branding) { this.branding = branding; }
}
