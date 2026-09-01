package com.digicart.store.controller;

import com.digicart.store.dto.CreateStoreRequest;
import com.digicart.store.dto.UpdateStoreRequest;
import com.digicart.store.entity.Store;
import com.digicart.store.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller exposing store HTTP APIs for <em>store-service</em>.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable String id) {
        return ResponseEntity.ok(storeService.findById(id));
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Store> findByAdminId(
            @PathVariable String adminId,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.findByAdminId(adminId));
    }

    @GetMapping("/subdomain/{subdomain}")
    public ResponseEntity<Store> findBySubdomain(@PathVariable String subdomain) {
        return ResponseEntity.ok(storeService.findBySubdomain(subdomain));
    }

    @PostMapping
    public ResponseEntity<Store> create(
            @Valid @RequestBody CreateStoreRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        request.setAdminId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update(
            @PathVariable String id,
            @RequestBody UpdateStoreRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Store>> findAllAlias(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.findAll());
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<Store>> findByIds(
            @RequestParam("ids") List<String> ids,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.findByIds(ids));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.getStats());
    }
}
