package com.digicart.store.controller;

import com.digicart.store.dto.AiConfigRequest;
import com.digicart.store.entity.AiConfig;
import com.digicart.store.service.AiConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for AI configuration and stub AI generation endpoints.
 */
@RestController
@RequestMapping("/api/store")
public class AiConfigController {

    private final AiConfigService aiConfigService;

    public AiConfigController(AiConfigService aiConfigService) {
        this.aiConfigService = aiConfigService;
    }

    @GetMapping
    public ResponseEntity<?> getStore(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole,
            @RequestHeader(value = "X-Store-Id", required = false) String storeId) {
        if ("user".equalsIgnoreCase(userRole)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }
        return ResponseEntity.ok(Map.of("storeId", storeId != null ? storeId : ""));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestHeader(value = "X-User-Role", required = false) String userRole,
            @RequestPart(value = "file", required = false) org.springframework.web.multipart.MultipartFile file) {
        if (file != null) {
            String ct = file.getContentType();
            List<String> allowed = List.of("image/jpeg", "image/png", "image/gif", "image/webp", "image/svg+xml");
            if (ct == null || !allowed.contains(ct)) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid file type"));
            }
        }
        return ResponseEntity.ok(Map.of("url", ""));
    }

    @GetMapping("/ai-settings")
    public ResponseEntity<AiConfig> getAiSettings(
            @RequestHeader("X-Store-Id") String storeId) {
        return ResponseEntity.ok(aiConfigService.findByStoreIdOrDefault(storeId));
    }

    @PatchMapping("/ai-settings")
    public ResponseEntity<AiConfig> updateAiSettings(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody AiConfigRequest request) {
        return ResponseEntity.ok(aiConfigService.upsert(storeId, request));
    }

    @PostMapping("/ai-generate")
    public ResponseEntity<Map<String, String>> aiGenerate(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(Map.of(
                "content", "Generated content placeholder — AI integration pending"));
    }

    @PostMapping("/ai-chat")
    public ResponseEntity<Map<String, String>> aiChat(
            @RequestHeader("X-Store-Id") String storeId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(Map.of("reply", "AI chat integration pending"));
    }
}
