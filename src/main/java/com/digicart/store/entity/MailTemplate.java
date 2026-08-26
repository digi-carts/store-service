package com.digicart.store.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity for per-store email templates keyed by event name.
 */
@Entity
@Table(
    name = "mail_template",
    schema = "store_svc",
    uniqueConstraints = @UniqueConstraint(columnNames = {"store_id", "event"})
)
@EntityListeners(AuditingEntityListener.class)
public class MailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @Column(name = "event", nullable = false)
    private String event;

    @Column(name = "subject")
    private String subject;

    @Column(name = "html_body", columnDefinition = "TEXT")
    private String htmlBody;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public MailTemplate() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getHtmlBody() { return htmlBody; }
    public void setHtmlBody(String htmlBody) { this.htmlBody = htmlBody; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
