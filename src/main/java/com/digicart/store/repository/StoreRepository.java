package com.digicart.store.repository;

import com.digicart.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for store  persistence.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    Optional<Store> findByAdminId(String adminId);
    Optional<Store> findBySubdomain(String subdomain);
    Optional<Store> findByStoreUrlId(String storeUrlId);
    List<Store> findByIdIn(List<UUID> ids);
    long countByPublishedTrue();
    boolean existsBySubdomain(String subdomain);
    boolean existsByAdminId(String adminId);
}
