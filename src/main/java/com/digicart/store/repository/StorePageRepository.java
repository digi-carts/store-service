package com.digicart.store.repository;

import com.digicart.store.entity.StorePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for store page  persistence.
 */
@Repository
public interface StorePageRepository extends JpaRepository<StorePage, String> {
    List<StorePage> findByStoreId(String storeId);
    Optional<StorePage> findByStoreIdAndSlug(String storeId, String slug);
}
