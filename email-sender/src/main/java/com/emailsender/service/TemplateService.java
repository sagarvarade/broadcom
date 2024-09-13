package com.emailsender.service;

import com.emailsender.beans.TemplatesGenerated;
import com.emailsender.repository.TemplateRepo;
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
