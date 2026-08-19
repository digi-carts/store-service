package com.digicart.store.repository;

import com.digicart.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for store  persistence.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    /**
     * Finds by admin id.
     *
     * @param adminId admin user identifier
     * @return the value if present
     */
    Optional<Store> findByAdminId(String adminId);
    /**
     * Finds by subdomain.
     *
     * @param subdomain store subdomain
     * @return the value if present
     */
    Optional<Store> findBySubdomain(String subdomain);
    /**
     * Finds by store url id.
     *
     * @param storeUrlId store url id
     * @return the value if present
     */
    Optional<Store> findByStoreUrlId(String storeUrlId);
    /**
     * Returns whether by subdomain exists.
     *
     * @param subdomain store subdomain
     * @return the boolean
     */
    boolean existsBySubdomain(String subdomain);
    /**
     * Returns whether by admin id exists.
     *
     * @param adminId admin user identifier
     * @return the boolean
     */
    boolean existsByAdminId(String adminId);
}
