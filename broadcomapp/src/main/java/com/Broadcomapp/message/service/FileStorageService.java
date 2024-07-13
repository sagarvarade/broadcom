package com.Broadcomapp.message.service;

import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/";

    @Autowired
    private FileStorageRepository fileStorageRepository;
    @Autowired
    private TemplateEngine templateEngine;

    public void saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        FileStorage fileEntity = new FileStorage();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        fileEntity.setActive(true);
        fileEntity.setFilePath(filePath.toString());
        fileStorageRepository.save(fileEntity);
    }


    public Optional<FileStorage> getFile(Long id) {
        return fileStorageRepository.findById(id);
    }

    public List<FileStorage> getActiveFiles() {
        return fileStorageRepository.findByIsActive(true);
    }

    public void deleteFile(Long id) {
        fileStorageRepository.deleteById(id);
    }

    public void updateFile(Long id, MultipartFile file, boolean isActive) throws IOException {
        Optional<FileStorage> existingFileOpt = fileStorageRepository.findById(id);
        if (existingFileOpt.isPresent()) {
            FileStorage existingFile = existingFileOpt.get();

            // Delete old file from filesystem
            Path oldFilePath = Paths.get(existingFile.getFilePath());
            Files.deleteIfExists(oldFilePath);

            // Save new file to filesystem
            String fileName = file.getOriginalFilename();
            Path newFilePath = Paths.get(uploadDir, fileName);
            Files.write(newFilePath, file.getBytes());

            // Update file metadata in database
            existingFile.setFileName(fileName);
            existingFile.setFilePath(newFilePath.toString());
            existingFile.setActive(isActive);
            existingFile.setData(file.getBytes());
            fileStorageRepository.save(existingFile);
        }
    }


    public Optional<FileStorage> findByFileNameAndIsActive(String templateName, boolean b) {
        return fileStorageRepository.findByFileNameAndIsActive(templateName,b);
    }
}
