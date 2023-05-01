package com.group11.msdocument.controller;

import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.dto.DocumentDto;
import com.group11.msdocument.model.dto.DocumentUpdateDto;
import com.group11.msdocument.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;


    @PostMapping("/upload")
    Document uploadDocument(@RequestBody DocumentDto documentDto){
        return documentService.uploadDocument(documentDto);
    }

    @PutMapping("/{documentId}")
    Document updateDocument(@RequestBody DocumentUpdateDto documentUpdateDto, @PathVariable Long documentId){
        return documentService.updateDocument(documentUpdateDto,documentId);
    }

    @GetMapping("/{documentId}")
    Document fetchDocument(@PathVariable Long documentId){
        return documentService.getDocumentByDocumentId(documentId);
    }

    @GetMapping("/customer/{customerId}")
    List<Document> getDocument(@PathVariable Long customerId){
        return documentService.getDocumentByCustomerId(customerId);
    }

    @DeleteMapping("/{document_id}")
    Void deleteDocument(@PathVariable Long document_id){
         return documentService.deleteDocument(document_id);
    }
}
