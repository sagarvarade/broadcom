package com.Broadcomapp.message.controller;

import com.Broadcomapp.message.Util.TemplateResolverService;
import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.beans.Template;
import com.Broadcomapp.message.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/broad-com-app/template")
public class MessageController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TemplateResolverService templateResolverService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("user_id") String userID) {
        Map<String,String> response = new HashMap<>();
        try {
            fileStorageService.saveFile(file,userID);
            response.put("ok", "File uploaded successfully!");
        } catch (IOException e) {
            response.put("error", "an error expected on processing file");
            return ResponseEntity.badRequest().body(response);
        }
        return null;
    }

    @GetMapping("get-file/{id}")
    public Optional<FileStorage> getFile(@PathVariable Long id,@RequestHeader("user_id") String userID) {
        return fileStorageService.getFileByFileNameAndUpdatedBy(id,userID);
    }

    @GetMapping("/get-active-files")
    public List<FileStorage> getActiveFiles(@RequestHeader("user_id") String userID) {
        return fileStorageService.getActiveFiles();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id,@RequestHeader("user_id") String userID) {
        try {
            fileStorageService.deleteFile(id);
            return "File deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete file!";
        }
    }

    @PutMapping("/update/{id}")
    public String updateFile(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("isActive") boolean isActive,@RequestHeader("user_id") String userID) {
        try {
            fileStorageService.updateFile(id, file, isActive,userID);
            return "File updated successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to update file!";
        }
    }

    @PostMapping("/render-test")
    public String renderTemplate(@RequestBody Template template) {
        System.out.println(template.getTemplateName()+"  "+template.getVariable());
        return templateResolverService.processTemplate(template.getTemplateName(), template.getVariable());
    }

    @PostMapping("/render-for-group-email/{group-name}")
    public String renderTemplateForGroupWithEmail(@PathVariable("group-name") String groupName,@RequestBody Template template,@RequestHeader("user_id") String userID) {
        return templateResolverService.processTemplateWithGroupEmail(groupName,template,userID);
    }

    @PostMapping("/render-for-group-sms/{group-name}")
    public String renderTemplateForGroupWithSMS(@PathVariable("group-name") String groupName,@RequestBody Template template,@RequestHeader("user_id") String userID) {
        return templateResolverService.processTemplateWithGroupSMS(groupName,template,userID);
    }

}
