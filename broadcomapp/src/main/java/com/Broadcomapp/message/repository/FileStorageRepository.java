package com.Broadcomapp.message.repository;

import com.Broadcomapp.message.beans.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
    List<FileStorage> findByIsActive(boolean b);

    FileStorage findByFileStorageIdAndIsActive(Long id,boolean isAct);

}
