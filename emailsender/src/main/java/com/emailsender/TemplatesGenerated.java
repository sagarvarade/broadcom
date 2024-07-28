package com.emailsender;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplatesGenerated {

    @Id
    @SequenceGenerator(
            name="template_generated_sequence",
            sequenceName = "template_generated_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "template_generated_sequence"
    )
    private Long templateId;
    private String templateName;
    private String groupName;
    private String sendToPhoneNumber;
    private String sendToEmail;

    @Lob
    @Column(name = "data", columnDefinition = "LONGTEXT")
    private String data;
    private String createdBy;
    private LocalDateTime createdDate;
}
