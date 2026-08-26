package com.digicart.store.dto;

/**
 * Request DTO for upserting AI configuration for a store.
 */
public class AiConfigRequest {
    private Boolean enabled;
    private String model;
    private String systemPrompt;

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }
}
