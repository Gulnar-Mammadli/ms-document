package com.group11.msdocument.service;

import com.group11.msdocument.mapper.DocumentMapper;
import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.dto.DocumentDto;
import com.group11.msdocument.model.dto.DocumentUpdateDto;
import com.group11.msdocument.model.enums.DocumentType;
import com.group11.msdocument.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;


    public Document uploadDocument(DocumentDto documentDto,MultipartFile file) throws IOException {
        Optional<Document> result = documentRepository.findByCustomerIdAndLoanIdAndDocumentType(documentDto.getCustomerId(),
                documentDto.getLoanId(), documentDto.getDocumentType());
        if (result.isEmpty()) {
            Document document = DocumentMapper.INSTANCE.mapToDocument(documentDto);
            document.setFileData(file.getBytes());
            document.setCreatedAt(LocalDateTime.now());
            return documentRepository.save(document);
        }
        return null;
    }
    //    TODO ancaq approved olmamislar update oluna biler?
    public Document updateDocument(DocumentUpdateDto documentUpdateDto, Long documentId) {
        Optional<Document> result = documentRepository.findById(documentId);
        if (result.isPresent()) {
            Document document = DocumentMapper.INSTANCE.mapFromDocumentUpdateDto(documentUpdateDto);
            document.setId(documentId);
            document.setApproved(true);
            document.setCreatedAt(result.get().getCreatedAt());
            document.setUpdatedAt(LocalDateTime.now());
            return documentRepository.save(document);
        }
        return null;
    }

    public List<Document> getDocumentByCustomerId(Long customerId) {
        Optional<List<Document>> list = documentRepository.findAllByCustomerId(customerId);
        return list.orElse(null);
    }

    public Document getDocumentByDocumentId(Long documentId) {
        Optional<Document> result = documentRepository.findById(documentId);
        return result.orElse(null);
    }

    //    TODO approve olunubsa delete oluna biler?
    public Void deleteDocument(Long document_id) {
        Optional<Document> result = documentRepository.findById(document_id);
        if (result.isPresent()) {
            documentRepository.deleteById(document_id);
        }
        return null;
    }


}
