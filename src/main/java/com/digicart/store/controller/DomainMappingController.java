package com.digicart.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Stub controller for custom domain mapping (Cloudflare integration pending).
 */
@RestController
@RequestMapping("/api/store/domain-mapping")
public class DomainMappingController {

    @GetMapping
    public ResponseEntity<?> listDomainMappings(
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        if (!"superadmin".equalsIgnoreCase(userRole)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> initiateDomainMapping(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(Map.of(
                "status", "PENDING",
                "message", "Domain mapping initiated"));
    }

    @GetMapping("/{domain}")
    public ResponseEntity<Map<String, String>> getDomainMapping(
            @RequestHeader("X-Store-Id") String storeId,
            @PathVariable String domain) {
        return ResponseEntity.ok(Map.of(
                "domain", domain,
                "status", "ACTIVE"));
    }

    @DeleteMapping("/{domain}")
    public ResponseEntity<Void> deleteDomainMapping(
            @RequestHeader("X-Store-Id") String storeId,
            @PathVariable String domain) {
        return ResponseEntity.noContent().build();
    }
}
