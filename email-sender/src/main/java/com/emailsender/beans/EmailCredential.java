package com.emailsender.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="email_credentials")
public class EmailCredential {
    @Id
    @SequenceGenerator(
            name="email_credentials_sequence",
            sequenceName = "email_credentials_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "email_credentials_sequence"
    )
    private Long emailCredentialId;
    private String userName;
    private String userPassword;
    private String emailHost;
    private int active;   // 1 active, 0 Disable

    private String createdBy;
    private LocalDateTime createdDate;

    private String updatedBy;
    private LocalDateTime updatedDate;
}
