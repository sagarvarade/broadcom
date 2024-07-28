package com.Broadcomapp.message.service;

import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.repository.TemplateGeneratedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateGenerateService {

    @Autowired
    private TemplateGeneratedRepository templateGeneratedRepository;

    public String save(TemplatesGenerated templatesGenerated){
        try {
            templateGeneratedRepository.save(templatesGenerated);
            return  "Success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Fail to save temp generated";
        }
    }
}
