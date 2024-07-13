package com.Broadcomapp.message.beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="file_storage")
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
    private byte[] data;
    private String filePath;
}
