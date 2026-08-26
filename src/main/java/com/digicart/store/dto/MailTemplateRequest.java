package com.digicart.store.dto;

/**
 * Request DTO for upserting a mail template for a store event.
 */
public class MailTemplateRequest {
    private String subject;
    private String htmlBody;

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getHtmlBody() { return htmlBody; }
    public void setHtmlBody(String htmlBody) { this.htmlBody = htmlBody; }
}
