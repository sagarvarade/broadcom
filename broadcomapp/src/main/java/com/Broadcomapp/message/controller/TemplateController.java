package com.Broadcomapp.message.controller;

import com.Broadcomapp.message.Util.TemplateResolverService;
import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.beans.Template;
import com.Broadcomapp.message.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TemplateResolverService templateResolverService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.saveFile(file);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file!";
        }
    }

    @GetMapping("get-file/{id}")
    public Optional<FileStorage> getFile(@PathVariable Long id) {
        return fileStorageService.getFile(id);
    }

    @GetMapping("/get-active-files")
    public List<FileStorage> getActiveFiles() {
        return fileStorageService.getActiveFiles();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        try {
            fileStorageService.deleteFile(id);
            return "File deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete file!";
        }
    }

    @PutMapping("/update/{id}")
    public String updateFile(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("isActive") boolean isActive) {
        try {
            fileStorageService.updateFile(id, file, isActive);
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

}
