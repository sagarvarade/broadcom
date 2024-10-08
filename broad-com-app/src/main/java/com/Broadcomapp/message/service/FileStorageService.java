package com.Broadcomapp.message.service;

import com.Broadcomapp.message.beans.FileStorage;
import com.Broadcomapp.message.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/";

    @Autowired
    private FileStorageRepository fileStorageRepository;


    public void saveFile(MultipartFile file,String userID) throws IOException {
        LocalDateTime now=LocalDateTime.now();
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        FileStorage fileEntity = new FileStorage();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        fileEntity.setActive(true);
        fileEntity.setFilePath(filePath.toString());
        fileEntity.setCreatedBy(userID);
        fileEntity.setUpdatedBy(userID);
        fileEntity.setCreatedDate(now);
        fileEntity.setUpdatedDate(now);
        fileStorageRepository.save(fileEntity);
    }


    public Optional<FileStorage> getFile(Long id) {
        return fileStorageRepository.findById(id);
    }

    public Optional<FileStorage> getFileByFileNameAndUpdatedBy(Long id,String userId) {
        return fileStorageRepository.findByFileStorageIdAndUpdatedBy(id,userId);
    }

    public List<FileStorage> getActiveFiles() {
        return fileStorageRepository.findByIsActive(true);
    }

    public void deleteFile(Long id) {
        fileStorageRepository.deleteById(id);
    }

    public void updateFile(Long id, MultipartFile file, boolean isActive,String userID) throws IOException {
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
            LocalDateTime now=LocalDateTime.now();
            // Update file metadata in database
            existingFile.setFileName(fileName);
            existingFile.setFilePath(newFilePath.toString());
            existingFile.setActive(isActive);
            existingFile.setData(file.getBytes());
            existingFile.setUpdatedBy(userID);
            existingFile.setUpdatedDate(now);
            fileStorageRepository.save(existingFile);
        }
    }


    public Optional<FileStorage> findByFileNameAndIsActive(String templateName, boolean b) {
        return fileStorageRepository.findByFileNameAndIsActive(templateName,b);
    }
}
