package com.digicart.store.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Map;

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
    private Map<String, Object> branding;

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
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
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public Map<String, Object> getBranding() { return branding; }
    public void setBranding(Map<String, Object> branding) { this.branding = branding; }
}
