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

/**
 * REST controller exposing store HTTP APIs for <em>store-service</em>.
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * Creates a new {@code StoreController}.
     *
     * @param storeService store service collaborator
     */
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Handles GET.
     * @return HTTP response
     */
    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }

    /**
     * Handles {@code GET /{id}}.
     *
     * @param id resource identifier
     * @return HTTP response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable String id) {
        return ResponseEntity.ok(storeService.findById(id));
    }

    /**
     * Handles {@code GET /admin/{adminId}}.
     *
     * @param adminId admin user identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param userRole caller role from the gateway ({@code X-User-Role})
     * @return HTTP response
     */
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Store> findByAdminId(
            @PathVariable String adminId,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.findByAdminId(adminId));
    }

    /**
     * Handles {@code GET /subdomain/{subdomain}}.
     *
     * @param subdomain store subdomain
     * @return HTTP response
     */
    @GetMapping("/subdomain/{subdomain}")
    public ResponseEntity<Store> findBySubdomain(@PathVariable String subdomain) {
        return ResponseEntity.ok(storeService.findBySubdomain(subdomain));
    }

    /**
     * Handles POST.
     *
     * @param request request payload
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param userRole caller role from the gateway ({@code X-User-Role})
     * @return HTTP response
     */
    @PostMapping
    public ResponseEntity<Store> create(
            @Valid @RequestBody CreateStoreRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(request));
    }

    /**
     * Handles {@code PUT /{id}}.
     *
     * @param id resource identifier
     * @param request request payload
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param userRole caller role from the gateway ({@code X-User-Role})
     * @return HTTP response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Store> update(
            @PathVariable String id,
            @RequestBody UpdateStoreRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storeService.update(id, request));
    }

    /**
     * Handles {@code DELETE /{id}}.
     *
     * @param id resource identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param userRole caller role from the gateway ({@code X-User-Role})
     * @return HTTP response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
