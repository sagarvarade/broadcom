package com.Broadcomapp.message.beans;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="file_storage", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fileName", "updatedBy"})
})
public class FileStorage {
    @Id
    @SequenceGenerator(
            name="file_storage_sequence",
            sequenceName = "file_storage_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_storage_sequence"
    )
    private Long fileStorageId;

    private String fileName;

    private boolean isActive;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;
    private String filePath;

    private String createdBy;
    private LocalDateTime createdDate;

    private String updatedBy;
    private LocalDateTime updatedDate;
}
