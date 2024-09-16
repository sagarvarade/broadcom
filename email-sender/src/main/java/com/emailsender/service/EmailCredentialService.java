package com.emailsender.service;

import com.emailsender.beans.EmailCredential;
import com.emailsender.repository.EmailCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailCredentialService {

    @Autowired
    private EmailCredentialRepo emailCredentialRepo;

    public EmailCredential saveEmailCredentials(EmailCredential emailCred){
        return  emailCredentialRepo.save(emailCred);
    }

    public Optional<EmailCredential> getById(Long id){
        return  emailCredentialRepo.findById(id);
    }
}
