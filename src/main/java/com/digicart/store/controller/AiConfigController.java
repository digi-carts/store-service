package com.digicart.store.controller;

import com.digicart.store.dto.AiConfigRequest;
import com.digicart.store.entity.AiConfig;
import com.digicart.store.entity.Store;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.service.AiConfigService;
import com.digicart.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for AI configuration. Identifies the merchant's store via X-User-Id.
 */
@RestController
@RequestMapping("/api/store")
public class AiConfigController {

    private final AiConfigService aiConfigService;
    private final StoreService storeService;

    public AiConfigController(AiConfigService aiConfigService, StoreService storeService) {
        this.aiConfigService = aiConfigService;
        this.storeService = storeService;
    }

    @GetMapping("/ai-settings")
    public ResponseEntity<AiConfig> getAiSettings(
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.ok(aiConfigService.findByStoreIdOrDefault(null));
        }
        try {
            Store store = storeService.findByAdminId(userId);
            return ResponseEntity.ok(aiConfigService.findByStoreIdOrDefault(store.getId().toString()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok(aiConfigService.findByStoreIdOrDefault(null));
        }
    }

    @PatchMapping("/ai-settings")
    public ResponseEntity<?> updateAiSettings(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody AiConfigRequest request) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Missing user identity"));
        }
        Store store = storeService.findByAdminId(userId);
        return ResponseEntity.ok(aiConfigService.upsert(store.getId().toString(), request));
    }

    @PostMapping("/ai-generate")
    public ResponseEntity<Map<String, String>> aiGenerate(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(Map.of("content", "Generated content placeholder — AI integration pending"));
    }

    @PostMapping("/ai-chat")
    public ResponseEntity<Map<String, String>> aiChat(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(Map.of("reply", "AI chat integration pending"));
    }
}
