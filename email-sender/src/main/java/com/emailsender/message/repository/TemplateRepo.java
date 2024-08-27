package com.emailsender.message.repository;

import com.emailsender.message.beans.TemplatesGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<TemplatesGenerated,Long> {
}
