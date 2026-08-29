package com.digicart.store.controller;

import com.digicart.store.dto.CreateStoreRequest;
import com.digicart.store.dto.UpdateDomainRequest;
import com.digicart.store.dto.UpdatePublishRequest;
import com.digicart.store.dto.UpdateStoreRequest;
import com.digicart.store.entity.Store;
import com.digicart.store.service.GcsStorageService;
import com.digicart.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Merchant-facing convenience controller — operates on the caller's own store
 * using the X-Store-Id header injected by the API gateway.
 */
@RestController
@RequestMapping("/api/store")
public class MerchantStoreController {

    private final StoreService storeService;
    private final GcsStorageService gcsStorageService;

    public MerchantStoreController(StoreService storeService, GcsStorageService gcsStorageService) {
        this.storeService = storeService;
        this.gcsStorageService = gcsStorageService;
    }

    @GetMapping
    public ResponseEntity<?> getMyStore(
            @RequestHeader(value = "X-Store-Id", required = false) String storeId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        if ("user".equalsIgnoreCase(userRole)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }
        if (storeId == null || storeId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
        return ResponseEntity.ok(storeService.findById(storeId));
    }

    @PatchMapping
    public ResponseEntity<Store> updateMyStore(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody UpdateStoreRequest request) {
        return ResponseEntity.ok(storeService.update(storeId, request));
    }

    @PatchMapping("/publish")
    public ResponseEntity<Store> updatePublish(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody UpdatePublishRequest request) {
        UpdateStoreRequest update = new UpdateStoreRequest();
        update.setPublished(request.getPublished());
        return ResponseEntity.ok(storeService.update(storeId, update));
    }

    @PatchMapping("/domain")
    public ResponseEntity<Store> updateDomain(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody UpdateDomainRequest request) {
        UpdateStoreRequest update = new UpdateStoreRequest();
        update.setDomain(request.getDomain());
        update.setStoreUrlId(request.getStoreUrlId());
        return ResponseEntity.ok(storeService.update(storeId, update));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestHeader(value = "X-Store-Id", required = false) String storeId,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No file provided"));
        }
        String ct = file.getContentType();
        List<String> allowed = List.of("image/jpeg", "image/png", "image/gif", "image/webp", "image/svg+xml");
        if (ct == null || !allowed.contains(ct)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid file type"));
        }
        if (!gcsStorageService.isConfigured()) {
            return ResponseEntity.status(503).body(Map.of("error", "File storage not configured"));
        }
        try {
            String url = gcsStorageService.upload(file);
            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Upload failed"));
        }
    }

    // ---- Admin endpoints ----

    @PostMapping("/admin-create")
    public ResponseEntity<Store> adminCreate(
            @RequestHeader(value = "X-User-Role", required = false) String userRole,
            @RequestBody CreateStoreRequest request) {
        if (!"ADMIN".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(request));
    }

    @PatchMapping("/admin-update/{id}")
    public ResponseEntity<Store> adminUpdate(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Role", required = false) String userRole,
            @RequestBody UpdateStoreRequest request) {
        if (!"ADMIN".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(storeService.update(id, request));
    }

    @DeleteMapping("/admin-delete/{id}")
    public ResponseEntity<Void> adminDelete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
