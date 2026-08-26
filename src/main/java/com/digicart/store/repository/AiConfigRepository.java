package com.digicart.store.repository;

import com.digicart.store.entity.AiConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for AiConfig persistence.
 */
@Repository
public interface AiConfigRepository extends JpaRepository<AiConfig, UUID> {
    Optional<AiConfig> findByStoreId(String storeId);
}
