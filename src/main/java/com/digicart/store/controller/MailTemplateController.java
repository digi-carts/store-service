package com.digicart.store.controller;

import com.digicart.store.dto.MailTemplateRequest;
import com.digicart.store.entity.MailTemplate;
import com.digicart.store.service.MailTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for per-store mail template management.
 */
@RestController
@RequestMapping("/api/store/mail-templates")
public class MailTemplateController {

    private final MailTemplateService mailTemplateService;

    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @GetMapping("/{event}")
    public ResponseEntity<MailTemplate> getTemplate(
            @RequestHeader("X-Store-Id") String storeId,
            @PathVariable String event) {
        return ResponseEntity.ok(mailTemplateService.findByStoreIdAndEvent(storeId, event));
    }

    @PatchMapping("/{event}")
    public ResponseEntity<MailTemplate> upsertTemplate(
            @RequestHeader("X-Store-Id") String storeId,
            @PathVariable String event,
            @RequestBody MailTemplateRequest request) {
        return ResponseEntity.ok(mailTemplateService.upsert(storeId, event, request));
    }
}
