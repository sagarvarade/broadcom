package com.Broadcomapp.message.service;

import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.repository.TemplateRepo;
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
