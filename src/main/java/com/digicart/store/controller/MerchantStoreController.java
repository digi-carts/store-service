package com.digicart.store.controller;

import com.digicart.store.dto.CreateStoreRequest;
import com.digicart.store.dto.UpdateDomainRequest;
import com.digicart.store.dto.UpdatePublishRequest;
import com.digicart.store.dto.UpdateStoreRequest;
import com.digicart.store.entity.Store;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.service.GcsStorageService;
import com.digicart.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Merchant-facing store controller — all operations identify the store via
 * the X-User-Id header injected by the API gateway (the merchant's own user ID).
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
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        if ("user".equalsIgnoreCase(userRole)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
        try {
            Store store = storeService.findByAdminId(userId);
            return ResponseEntity.ok(Map.of("store", store));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createMyStore(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole,
            @RequestBody UpdateStoreRequest request) {
        if ("user".equalsIgnoreCase(userRole)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Missing user identity"));
        }
        // Idempotent: if store already exists for this merchant, return it
        try {
            Store existing = storeService.findByAdminId(userId);
            return ResponseEntity.ok(existing);
        } catch (EntityNotFoundException ignored) { /* fall through to create */ }

        String name = request.getName();
        String subdomain = request.getSubdomain();
        if (name == null || name.isBlank() || subdomain == null || subdomain.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "name and subdomain are required"));
        }
        CreateStoreRequest create = new CreateStoreRequest();
        create.setAdminId(userId);
        create.setName(name.trim());
        create.setSubdomain(subdomain.trim());
        create.setStoreUrlId(request.getStoreUrlId() != null ? request.getStoreUrlId().trim() : subdomain.trim());
        create.setEmail(request.getEmail());
        create.setPhone(request.getPhone());
        if (request.getCurrency() != null) create.setCurrency(request.getCurrency());
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(create));
    }

    @PatchMapping
    public ResponseEntity<?> updateMyStore(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody UpdateStoreRequest request) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
        Store store = storeService.findByAdminId(userId);
        return ResponseEntity.ok(storeService.update(store.getId().toString(), request));
    }

    @PatchMapping("/publish")
    public ResponseEntity<?> updatePublish(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody UpdatePublishRequest request) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
        Store store = storeService.findByAdminId(userId);
        UpdateStoreRequest update = new UpdateStoreRequest();
        update.setPublished(request.getPublished());
        return ResponseEntity.ok(storeService.update(store.getId().toString(), update));
    }

    @PatchMapping("/domain")
    public ResponseEntity<?> updateDomain(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody UpdateDomainRequest request) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No store found"));
        }
        Store store = storeService.findByAdminId(userId);
        UpdateStoreRequest update = new UpdateStoreRequest();
        update.setDomain(request.getDomain());
        update.setStoreUrlId(request.getStoreUrlId());
        return ResponseEntity.ok(storeService.update(store.getId().toString(), update));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
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
