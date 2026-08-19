package com.digicart.store.service;

import com.digicart.store.dto.CreateStorePageRequest;
import com.digicart.store.dto.UpdateStorePageRequest;
import com.digicart.store.entity.StorePage;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.repository.StorePageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application service implementing store page use cases for <em>store-service</em>.
 */
@Service
public class StorePageService {

    private final StorePageRepository storePageRepository;

    /**
     * Creates a new {@code StorePageService}.
     *
     * @param storePageRepository store page repository collaborator
     */
    public StorePageService(StorePageRepository storePageRepository) {
        this.storePageRepository = storePageRepository;
    }

    /**
     * Finds all.
     * @return matching records
     */
    public List<StorePage> findAll() {
        return storePageRepository.findAll();
    }

    /**
     * Finds by id.
     *
     * @param id resource identifier
     * @return the store page
     */
    public StorePage findById(String id) {
        return storePageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StorePage not found with id: " + id));
    }

    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @return matching records
     */
    public List<StorePage> findByStoreId(String storeId) {
        return storePageRepository.findByStoreId(storeId);
    }

    /**
     * Finds by store id and slug.
     *
     * @param storeId store (tenant) identifier
     * @param slug page slug
     * @return the store page
     */
    public StorePage findByStoreIdAndSlug(String storeId, String slug) {
        return storePageRepository.findByStoreIdAndSlug(storeId, slug)
                .orElseThrow(() -> new EntityNotFoundException("StorePage not found for storeId: " + storeId + " and slug: " + slug));
    }

    /**
     * Creates a new record.
     *
     * @param request request payload
     * @return the store page
     */
    public StorePage create(CreateStorePageRequest request) {
        StorePage page = new StorePage();
        page.setStoreId(request.getStoreId());
        page.setSlug(request.getSlug());
        page.setTitle(request.getTitle());
        page.setContent(request.getContent());
        if (request.getPublished() != null) page.setPublished(request.getPublished());
        return storePageRepository.save(page);
    }

    /**
     * Updates an existing record.
     *
     * @param id resource identifier
     * @param request request payload
     * @return the store page
     */
    public StorePage update(String id, UpdateStorePageRequest request) {
        StorePage page = findById(id);
        if (request.getSlug() != null) page.setSlug(request.getSlug());
        if (request.getTitle() != null) page.setTitle(request.getTitle());
        if (request.getContent() != null) page.setContent(request.getContent());
        if (request.getPublished() != null) page.setPublished(request.getPublished());
        return storePageRepository.save(page);
    }

    /**
     * Deletes the record.
     *
     * @param id resource identifier
     */
    public void delete(String id) {
        if (!storePageRepository.existsById(id)) {
            throw new EntityNotFoundException("StorePage not found with id: " + id);
        }
        storePageRepository.deleteById(id);
    }
}
