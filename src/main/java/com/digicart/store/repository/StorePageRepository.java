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
    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @return matching records
     */
    List<StorePage> findByStoreId(String storeId);
    /**
     * Finds by store id and slug.
     *
     * @param storeId store (tenant) identifier
     * @param slug page slug
     * @return the value if present
     */
    Optional<StorePage> findByStoreIdAndSlug(String storeId, String slug);
}
