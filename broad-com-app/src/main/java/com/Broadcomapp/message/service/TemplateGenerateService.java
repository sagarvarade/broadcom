package com.Broadcomapp.message.service;

import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.repository.TemplateGeneratedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemplateGenerateService {

    @Autowired
    private TemplateGeneratedRepository templateGeneratedRepository;

    public String save(TemplatesGenerated templatesGenerated){
        try {
            templateGeneratedRepository.save(templatesGenerated);
            log.info("Template Generate Service saved");
            return  "Success";
        }
        catch (Exception e){
            log.error("Failed to save generated template : {} ", e.getMessage());
            return "Fail to save temp generated";
        }
    }
}
