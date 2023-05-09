package com.group11.msdocument.controller;

import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.dto.DocumentDto;
import com.group11.msdocument.model.dto.DocumentUpdateDto;
import com.group11.msdocument.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;


    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE,
                                                    MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody Document uploadDocument(@RequestPart("dto") @Valid DocumentDto documentDto,
                            @RequestPart("file") MultipartFile file) throws IOException {
        return documentService.uploadDocument(documentDto, file);
    }


//TODO
    @GetMapping("/read/{fileName}")
    String readFile(@PathVariable String fileName) throws IOException {
        return documentService.downloadFile(fileName);
    }

    @PutMapping("/{documentId}")
    Document updateDocument(@RequestPart("dto") DocumentUpdateDto documentUpdateDto,
                            @RequestPart("file") MultipartFile file,
                            @PathVariable Long documentId) throws IOException {
        return documentService.updateDocument(documentUpdateDto,file, documentId);
    }

    @GetMapping("/documentId/{documentId}")
    Document fetchDocument(@PathVariable Long documentId) {
        return documentService.getDocumentByDocumentId(documentId);
    }

    @GetMapping("/customer/{customerId}")
    List<Document> getDocument(@PathVariable Long customerId) {
        return documentService.getDocumentByCustomerId(customerId);
    }

    @DeleteMapping("/{document_id}")
    Void deleteDocument(@PathVariable Long document_id) {
        return documentService.deleteDocument(document_id);
    }
}
