package com.emailsender.repository;

import com.emailsender.beans.TemplatesGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<TemplatesGenerated,Long> {
}
