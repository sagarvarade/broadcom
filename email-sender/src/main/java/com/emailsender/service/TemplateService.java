package com.emailsender.service;

import com.emailsender.beans.TemplatesGenerated;
import com.emailsender.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepo;

    public void save(TemplatesGenerated temp){
        templateRepo.save(temp);
    }
}
