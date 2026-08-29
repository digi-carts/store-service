package com.digicart.store.service;

import com.digicart.store.dto.AiConfigRequest;
import com.digicart.store.entity.AiConfig;
import com.digicart.store.repository.AiConfigRepository;
import org.springframework.stereotype.Service;

/**
 * Application service for AI configuration use cases.
 */
@Service
public class AiConfigService {

    private final AiConfigRepository aiConfigRepository;

    public AiConfigService(AiConfigRepository aiConfigRepository) {
        this.aiConfigRepository = aiConfigRepository;
    }

    public AiConfig findByStoreIdOrDefault(String storeId) {
        return aiConfigRepository.findByStoreId(storeId).orElseGet(() -> {
            AiConfig defaults = new AiConfig();
            defaults.setStoreId(storeId);
            defaults.setEnabled(false);
            return defaults;
        });
    }

    public AiConfig upsert(String storeId, AiConfigRequest request) {
        AiConfig config = aiConfigRepository.findByStoreId(storeId).orElseGet(() -> {
            AiConfig c = new AiConfig();
            c.setStoreId(storeId);
            return c;
        });
        if (request.getEnabled() != null) config.setEnabled(request.getEnabled());
        if (request.getModel() != null) config.setModel(request.getModel());
        if (request.getSystemPrompt() != null) config.setSystemPrompt(request.getSystemPrompt());
        return aiConfigRepository.save(config);
    }
}
