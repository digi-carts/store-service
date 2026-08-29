package com.digicart.store.controller;

import com.digicart.store.dto.AiConfigRequest;
import com.digicart.store.entity.AiConfig;
import com.digicart.store.service.AiConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ai-settings")
    public ResponseEntity<AiConfig> getAiSettings(
            @RequestHeader(value = "X-Store-Id", required = false) String storeId) {
        if (storeId == null || storeId.isBlank()) {
            return ResponseEntity.ok(aiConfigService.findByStoreIdOrDefault(null));
        }
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
