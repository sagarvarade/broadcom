package com.Broadcomapp.message.controller;

import com.Broadcomapp.filter.JwtAuthFilter;
import com.Broadcomapp.message.Util.TemplateResolverService;
import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.beans.Template;
import com.Broadcomapp.message.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final Logger log = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TemplateResolverService templateResolverService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          @RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template/upload called ");
        log.info("User Id  , {} ",userID);
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
    public ResponseEntity<FileStorage> getFile(@PathVariable Long id,@RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template/get-file/{id} called ");
        log.info("File Id : {} ,User Id  , {} ",id,userID);
        Optional<FileStorage> optionalFileStorage=fileStorageService.getFileByFileNameAndUpdatedBy(id,userID);
        return optionalFileStorage.map(fileStorage -> new ResponseEntity<>(fileStorage, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-active-files")
    public ResponseEntity<List<FileStorage>> getActiveFiles(@RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template/get-active-files called ");
        log.info("User Id  , {} ",userID);
        return new ResponseEntity<List<FileStorage>>(fileStorageService.getActiveFiles(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id,@RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template//delete/{id} called ");
        log.info("User Id  , {} ",userID);
        try {
            fileStorageService.deleteFile(id);
            return new ResponseEntity<>("File deleted successfully!",HttpStatus.OK);
        } catch (Exception e) {
            log.info("Exception while deleting : {} "+e.getMessage());
            return new ResponseEntity<>("Failed to delete file!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFile(@PathVariable Long id,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("isActive") boolean isActive,
                             @RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template/update/{id} called ");
        log.info("User Id  , {} ",userID);
        try {
            fileStorageService.updateFile(id, file, isActive,userID);
            return new ResponseEntity<>("File updated successfully!",HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update file!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/render-test")
    public ResponseEntity<String> renderTemplate(@RequestBody Template template) {
        log.info("/broad-com-app/template/render-test called ");
        log.info("Template Name  {}, Template Variable {} ",template.getTemplateName(),template.getVariable());
        return new ResponseEntity<>(templateResolverService.processTemplate(template.getTemplateName(), template.getVariable()),HttpStatus.OK);
    }

    @PostMapping("/render-for-group-email/{group-name}")
    public ResponseEntity<String> renderTemplateForGroupWithEmail(@PathVariable("group-name") String groupName,
                                                  @RequestBody Template template,
                                                  @RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template/render-for-group-email/{group-name} called ");
        log.info("Template Name  {}, Template Variable {} ",template.getTemplateName(),template.getVariable());
        log.info("Template groupName  {}, Template Name {}, Template Variable {} ",groupName,template.getTemplateName(),template.getVariable());
        log.info("User Id  , {} ",userID);
        return new ResponseEntity<>(templateResolverService.processTemplateWithGroupEmail(groupName,template,userID),HttpStatus.OK);
    }

    @PostMapping("/render-for-group-sms/{group-name}")
    public ResponseEntity<String> renderTemplateForGroupWithSMS(@PathVariable("group-name") String groupName,
                                                @RequestBody Template template,
                                                @RequestHeader("user_id") String userID) {
        log.info("/broad-com-app/template//render-for-group-sms/{group-name} called ");
        log.info("Template Name  {}, Template Variable {} ",template.getTemplateName(),template.getVariable());
        log.info("Template groupName  {}, Template Name {}, Template Variable {} ",groupName,template.getTemplateName(),template.getVariable());
        log.info("User Id  , {} ",userID);
        return new ResponseEntity<>(templateResolverService.processTemplateWithGroupSMS(groupName,template,userID),HttpStatus.OK);
    }

}
