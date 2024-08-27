package com.smssender.message.repository;

import com.smssender.message.beans.TemplatesGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<TemplatesGenerated,Long> {
}
