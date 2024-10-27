package com.smssender.service;

import com.smssender.beans.TemplatesGenerated;
import com.smssender.repository.TemplateRepository;
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
