package com.digicart.store.service;

import com.digicart.store.dto.CreateStorePageRequest;
import com.digicart.store.dto.UpdateStorePageRequest;
import com.digicart.store.entity.StorePage;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.repository.StorePageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorePageService {

    private final StorePageRepository storePageRepository;

    public StorePageService(StorePageRepository storePageRepository) {
        this.storePageRepository = storePageRepository;
    }

    public List<StorePage> findAll() {
        return storePageRepository.findAll();
    }

    public StorePage findById(String id) {
        return storePageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StorePage not found with id: " + id));
    }

    public List<StorePage> findByStoreId(String storeId) {
        return storePageRepository.findByStoreId(storeId);
    }

    public StorePage findByStoreIdAndSlug(String storeId, String slug) {
        return storePageRepository.findByStoreIdAndSlug(storeId, slug)
                .orElseThrow(() -> new EntityNotFoundException("StorePage not found for storeId: " + storeId + " and slug: " + slug));
    }

    public StorePage create(CreateStorePageRequest request) {
        StorePage page = new StorePage();
        page.setStoreId(request.getStoreId());
        page.setSlug(request.getSlug());
        page.setTitle(request.getTitle());
        page.setContent(request.getContent());
        if (request.getPublished() != null) page.setPublished(request.getPublished());
        return storePageRepository.save(page);
    }

    public StorePage update(String id, UpdateStorePageRequest request) {
        StorePage page = findById(id);
        if (request.getSlug() != null) page.setSlug(request.getSlug());
        if (request.getTitle() != null) page.setTitle(request.getTitle());
        if (request.getContent() != null) page.setContent(request.getContent());
        if (request.getPublished() != null) page.setPublished(request.getPublished());
        return storePageRepository.save(page);
    }

    public void delete(String id) {
        if (!storePageRepository.existsById(id)) {
            throw new EntityNotFoundException("StorePage not found with id: " + id);
        }
        storePageRepository.deleteById(id);
    }
}
