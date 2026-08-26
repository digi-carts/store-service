package com.digicart.store.service;

import com.digicart.store.dto.MailTemplateRequest;
import com.digicart.store.entity.MailTemplate;
import com.digicart.store.exception.EntityNotFoundException;
import com.digicart.store.repository.MailTemplateRepository;
import org.springframework.stereotype.Service;

/**
 * Application service for mail template use cases.
 */
@Service
public class MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;

    public MailTemplateService(MailTemplateRepository mailTemplateRepository) {
        this.mailTemplateRepository = mailTemplateRepository;
    }

    public MailTemplate findByStoreIdAndEvent(String storeId, String event) {
        return mailTemplateRepository.findByStoreIdAndEvent(storeId, event)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Mail template not found for event: " + event));
    }

    public MailTemplate upsert(String storeId, String event, MailTemplateRequest request) {
        MailTemplate template = mailTemplateRepository
                .findByStoreIdAndEvent(storeId, event)
                .orElseGet(() -> {
                    MailTemplate t = new MailTemplate();
                    t.setStoreId(storeId);
                    t.setEvent(event);
                    return t;
                });
        if (request.getSubject() != null) template.setSubject(request.getSubject());
        if (request.getHtmlBody() != null) template.setHtmlBody(request.getHtmlBody());
        return mailTemplateRepository.save(template);
    }
}
