package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject,'ROLE_ADMIN')")
    public Document getDocument(String code){
        return documentRepository.findDocument(code);
    }
    @PreAuthorize("hasPermission(#code,'document','ROLE_ADMIN')")
    public Document getDocumentV2(String code){
        return documentRepository.findDocument(code);
    }
}
