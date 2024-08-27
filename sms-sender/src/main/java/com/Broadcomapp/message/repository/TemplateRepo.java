package com.Broadcomapp.message.repository;

import com.Broadcomapp.message.beans.TemplatesGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<TemplatesGenerated,Long> {
}
