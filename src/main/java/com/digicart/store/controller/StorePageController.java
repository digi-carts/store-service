package com.digicart.store.controller;

import com.digicart.store.dto.CreateStorePageRequest;
import com.digicart.store.dto.UpdateStorePageRequest;
import com.digicart.store.entity.StorePage;
import com.digicart.store.service.StorePageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store-pages")
public class StorePageController {

    private final StorePageService storePageService;

    public StorePageController(StorePageService storePageService) {
        this.storePageService = storePageService;
    }

    @GetMapping
    public ResponseEntity<List<StorePage>> findAll() {
        return ResponseEntity.ok(storePageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorePage> findById(@PathVariable String id) {
        return ResponseEntity.ok(storePageService.findById(id));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StorePage>> findByStoreId(
            @PathVariable String storeId,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storePageService.findByStoreId(storeId));
    }

    @GetMapping("/store/{storeId}/slug/{slug}")
    public ResponseEntity<StorePage> findByStoreIdAndSlug(
            @PathVariable String storeId,
            @PathVariable String slug) {
        return ResponseEntity.ok(storePageService.findByStoreIdAndSlug(storeId, slug));
    }

    @PostMapping
    public ResponseEntity<StorePage> create(
            @Valid @RequestBody CreateStorePageRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storePageService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorePage> update(
            @PathVariable String id,
            @RequestBody UpdateStorePageRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storePageService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        storePageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
