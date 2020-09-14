package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public void createCredential(Credential credential) {
        credentialMapper.insertCredential(credential);
    }

    public Credential getCredential(int credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    public List<Credential> getAllCredentials(int userId) {
        return credentialMapper.getCredentialByUser(userId);
    }

    public void editCredential(Credential credential) {
        credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }
}
