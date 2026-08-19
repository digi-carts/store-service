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

/**
 * REST controller exposing store page HTTP APIs for <em>store-service</em>.
 */
@RestController
@RequestMapping("/store-pages")
public class StorePageController {

    private final StorePageService storePageService;

    /**
     * Creates a new {@code StorePageController}.
     *
     * @param storePageService store page service collaborator
     */
    public StorePageController(StorePageService storePageService) {
        this.storePageService = storePageService;
    }

    /**
     * Handles GET.
     * @return HTTP response
     */
    @GetMapping
    public ResponseEntity<List<StorePage>> findAll() {
        return ResponseEntity.ok(storePageService.findAll());
    }

    /**
     * Handles {@code GET /{id}}.
     *
     * @param id resource identifier
     * @return HTTP response
     */
    @GetMapping("/{id}")
    public ResponseEntity<StorePage> findById(@PathVariable String id) {
        return ResponseEntity.ok(storePageService.findById(id));
    }

    /**
     * Handles {@code GET /store/{storeId}}.
     *
     * @param storeId store (tenant) identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param userRole caller role from the gateway ({@code X-User-Role})
     * @return HTTP response
     */
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StorePage>> findByStoreId(
            @PathVariable String storeId,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storePageService.findByStoreId(storeId));
    }

    /**
     * Handles {@code GET /store/{storeId}/slug/{slug}}.
     *
     * @param storeId store (tenant) identifier
     * @param slug page slug
     * @return HTTP response
     */
    @GetMapping("/store/{storeId}/slug/{slug}")
    public ResponseEntity<StorePage> findByStoreIdAndSlug(
            @PathVariable String storeId,
            @PathVariable String slug) {
        return ResponseEntity.ok(storePageService.findByStoreIdAndSlug(storeId, slug));
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
    public ResponseEntity<StorePage> create(
            @Valid @RequestBody CreateStorePageRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storePageService.create(request));
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
    public ResponseEntity<StorePage> update(
            @PathVariable String id,
            @RequestBody UpdateStorePageRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        return ResponseEntity.ok(storePageService.update(id, request));
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
        storePageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
