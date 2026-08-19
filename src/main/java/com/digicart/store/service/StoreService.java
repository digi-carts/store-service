package com.digicart.store.service;

import com.digicart.store.dto.CreateStoreRequest;
import com.digicart.store.dto.UpdateStoreRequest;
import com.digicart.store.entity.Store;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application service implementing store use cases for <em>store-service</em>.
 */
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    /**
     * Creates a new {@code StoreService}.
     *
     * @param storeRepository store repository collaborator
     */
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Finds all.
     * @return matching records
     */
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    /**
     * Finds by id.
     *
     * @param id resource identifier
     * @return the store
     */
    public Store findById(String id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
    }

    /**
     * Finds by admin id.
     *
     * @param adminId admin user identifier
     * @return the store
     */
    public Store findByAdminId(String adminId) {
        return storeRepository.findByAdminId(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found for adminId: " + adminId));
    }

    /**
     * Finds by subdomain.
     *
     * @param subdomain store subdomain
     * @return the store
     */
    public Store findBySubdomain(String subdomain) {
        return storeRepository.findBySubdomain(subdomain)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with subdomain: " + subdomain));
    }

    /**
     * Creates a new record.
     *
     * @param request request payload
     * @return the store
     */
    public Store create(CreateStoreRequest request) {
        Store store = new Store();
        store.setAdminId(request.getAdminId());
        store.setName(request.getName());
        store.setSubdomain(request.getSubdomain());
        store.setStoreUrlId(request.getStoreUrlId());
        store.setDomain(request.getDomain());
        store.setEmail(request.getEmail());
        store.setPhone(request.getPhone());
        if (request.getCurrency() != null) store.setCurrency(request.getCurrency());
        if (request.getPublished() != null) store.setPublished(request.getPublished());
        if (request.getLive() != null) store.setLive(request.getLive());
        if (request.getTemplate() != null) store.setTemplate(request.getTemplate());
        if (request.getBranding() != null) store.setBranding(request.getBranding());
        return storeRepository.save(store);
    }

    /**
     * Updates an existing record.
     *
     * @param id resource identifier
     * @param request request payload
     * @return the store
     */
    public Store update(String id, UpdateStoreRequest request) {
        Store store = findById(id);
        if (request.getName() != null) store.setName(request.getName());
        if (request.getSubdomain() != null) store.setSubdomain(request.getSubdomain());
        if (request.getStoreUrlId() != null) store.setStoreUrlId(request.getStoreUrlId());
        if (request.getDomain() != null) store.setDomain(request.getDomain());
        if (request.getEmail() != null) store.setEmail(request.getEmail());
        if (request.getPhone() != null) store.setPhone(request.getPhone());
        if (request.getCurrency() != null) store.setCurrency(request.getCurrency());
        if (request.getPublished() != null) store.setPublished(request.getPublished());
        if (request.getLive() != null) store.setLive(request.getLive());
        if (request.getVisitCount() != null) store.setVisitCount(request.getVisitCount());
        if (request.getAvailableDays() != null) store.setAvailableDays(request.getAvailableDays());
        if (request.getTemplate() != null) store.setTemplate(request.getTemplate());
        if (request.getBranding() != null) store.setBranding(request.getBranding());
        return storeRepository.save(store);
    }

    /**
     * Deletes the record.
     *
     * @param id resource identifier
     */
    public void delete(String id) {
        if (!storeRepository.existsById(id)) {
            throw new EntityNotFoundException("Store not found with id: " + id);
        }
        storeRepository.deleteById(id);
    }
}
