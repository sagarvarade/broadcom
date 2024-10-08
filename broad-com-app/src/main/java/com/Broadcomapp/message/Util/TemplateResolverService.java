package com.Broadcomapp.message.Util;

import com.Broadcomapp.broadcast.beans.BroadUser;
import com.Broadcomapp.broadcast.service.BroadCastGroupService;
import com.Broadcomapp.message.KafkaProducer.KafkaTemplateProducer;
import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.beans.Template;
import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.service.FileStorageService;
import com.Broadcomapp.message.service.TemplateGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TemplateResolverService {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private BroadCastGroupService broadCastGroupService;

    @Autowired
    private TemplateGenerateService templateGenerateService;

    @Autowired
    private KafkaTemplateProducer kafkaTemplatesProducer;

    public String processTemplate(String templateName, Map<String, Object> variables) {
        Optional<FileStorage> templateOpt = fileStorageService.findByFileNameAndIsActive(templateName, true);

        if (templateOpt.isPresent()) {
            byte[] templateContentBytes = templateOpt.get().getData();
            String templateContent = new String(templateContentBytes, StandardCharsets.UTF_8);
            Context context = new Context();
            context.setVariables(variables);
            return templateEngine.process(templateContent, context);
        } else {
            return "Not Found";
        }
    }

    public String processTemplateWithGroupEmail(String groupName,Template template,String loggedUser) {
        HashMap<String, List<BroadUser>> groupWithUsers= broadCastGroupService.getGroupDetails(groupName,loggedUser);
        String templateName=template.getTemplateName();
        Map<String, Object> variables=template.getVariable();
        Optional<FileStorage> templateOpt = fileStorageService.findByFileNameAndIsActive(templateName, true);

        if (templateOpt.isPresent()) {
            List<BroadUser> broadUsersList=groupWithUsers.get(groupName);
            byte[] templateContentBytes = templateOpt.get().getData();
            String templateContent = new String(templateContentBytes, StandardCharsets.UTF_8);
            Context context = new Context();

            for(BroadUser br:broadUsersList){
                variables.put("userName",br.getName());
                variables.put("userEmail",br.getEmail());
                variables.put("userPhoneNumber",br.getPhoneNumber());
                variables.put("userDateOfBirth",br.getDateOfBirth());
                variables.put("userGender",br.getGender());
                context.setVariables(variables);
                String renderedTemplate=templateEngine.process(templateContent, context);
                TemplatesGenerated tempGen=TemplatesGenerated.builder()
                        .groupName(groupName)
                        .createdBy(loggedUser)
                        .templateName(templateName)
                        .data(renderedTemplate)
                        .createdDate(LocalDateTime.now())
                        .sendToPhoneNumber(br.getPhoneNumber())
                        .sendToEmail(br.getEmail())
                        .type("email")
                        .build();
                templateGenerateService.save(tempGen);
                kafkaTemplatesProducer.sendTemplateToTopic("broad-email-topic","email",tempGen);
            }
            return "check log";
        } else {
            return "Not Found";
        }
    }

    public String processTemplateWithGroupSMS(String groupName,Template template,String loggedUser) {
        HashMap<String, List<BroadUser>> groupWithUsers= broadCastGroupService.getGroupDetails(groupName,loggedUser);
        String templateName=template.getTemplateName();
        Map<String, Object> variables=template.getVariable();
        Optional<FileStorage> templateOpt = fileStorageService.findByFileNameAndIsActive(templateName, true);

        if (templateOpt.isPresent()) {
            List<BroadUser> broadUsersList=groupWithUsers.get(groupName);
            byte[] templateContentBytes = templateOpt.get().getData();
            String templateContent = new String(templateContentBytes, StandardCharsets.UTF_8);
            Context context = new Context();

            for(BroadUser br:broadUsersList){
                variables.put("userName",br.getName());
                variables.put("userEmail",br.getEmail());
                variables.put("userPhoneNumber",br.getPhoneNumber());
                variables.put("userDateOfBirth",br.getDateOfBirth());
                variables.put("userGender",br.getGender());
                context.setVariables(variables);
                String renderedTemplate=templateEngine.process(templateContent, context);

                TemplatesGenerated tempGen=TemplatesGenerated.builder()
                        .groupName(groupName)
                        .createdBy(loggedUser)
                        .templateName(templateName)
                        .data(renderedTemplate)
                        .createdDate(LocalDateTime.now())
                        .sendToPhoneNumber(br.getPhoneNumber())
                        .sendToEmail(br.getEmail())
                        .type("sms")
                        .build();
                templateGenerateService.save(tempGen);
                kafkaTemplatesProducer.sendTemplateToTopic("broad-sms-topic","sms",tempGen);
            }
            return "check log";
        } else {
            return "Not Found";
        }
    }
}
