package com.emailsender.message.service;

import com.emailsender.message.beans.TemplatesGenerated;
import com.emailsender.message.repository.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepo templateRepo;

    public void save(TemplatesGenerated temp){
        templateRepo.save(temp);
    }
}
