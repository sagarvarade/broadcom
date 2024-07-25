package com.Broadcomapp.message.Util;

import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class TemplateResolverService {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TemplateEngine templateEngine;

    public String processTemplate(String templateName, Map<String, Object> variables) {
        Optional<FileStorage> templateOpt = fileStorageService.findByFileNameAndIsActive(templateName, true);

        if (templateOpt.isPresent()) {
            byte[] templateContentBytes = templateOpt.get().getData();
            String templateContent = new String(templateContentBytes, StandardCharsets.UTF_8);
            Context context = new Context();
            context.setVariables(variables);
            System.out.println("tempalte :"+templateOpt.get().getFileName()+"  "+context);
            System.out.println("Variable : "+variables);

            System.out.println(templateContent+"  "+context);
            System.out.println("Output : "+templateEngine.process(templateContent, context));
            return templateEngine.process(templateContent, context);
        } else {
            return "Not Found";
        }
    }
}
