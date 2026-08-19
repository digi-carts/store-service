package com.digicart.store.repository;

import com.digicart.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findByAdminId(String adminId);
    Optional<Store> findBySubdomain(String subdomain);
    Optional<Store> findByStoreUrlId(String storeUrlId);
    boolean existsBySubdomain(String subdomain);
    boolean existsByAdminId(String adminId);
}
