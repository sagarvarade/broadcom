package com.emailsender.repository;

import com.emailsender.beans.EmailCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCredentialRepo extends JpaRepository<EmailCredential,Long> {
}
