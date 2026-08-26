package com.digicart.store.repository;

import com.digicart.store.entity.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for MailTemplate persistence.
 */
@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, UUID> {
    Optional<MailTemplate> findByStoreIdAndEvent(String storeId, String event);
}
