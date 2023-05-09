package com.group11.msdocument.service;

import com.group11.msdocument.mapper.DocumentMapper;
import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.dto.DocumentDto;
import com.group11.msdocument.model.dto.DocumentUpdateDto;
import com.group11.msdocument.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

   private final String FOLDER_PATH= "C:/Users/mammadli/Desktop/UploadedFiles/";
    public Document uploadDocument(DocumentDto documentDto,MultipartFile file) throws IOException {
        Optional<Document> result = documentRepository.findByCustomerIdAndLoanIdAndDocumentType(documentDto.getCustomerId(),
                documentDto.getLoanId(), documentDto.getDocumentType());
        if (result.isEmpty()) {
            String filePath = FOLDER_PATH + file.getOriginalFilename();
            Document document = DocumentMapper.INSTANCE.mapToDocument(documentDto);
            document.setFilePath(filePath);
            document.setFileName(file.getOriginalFilename());
            document.setCreatedAt(LocalDateTime.now());
            file.transferTo(new File(filePath));
            return documentRepository.save(document);
        }
        return null;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        Optional<Document> fileData = documentRepository.findByFileName(fileName);
        if(fileData.isPresent()){
            String filePath = fileData.get().getFilePath();
            byte[] response = Files.readAllBytes(new File(filePath).toPath());
            return response;
        }
        return null;
    }


    //    TODO ancaq approved olmamislar update oluna biler?
    public Document updateDocument(DocumentUpdateDto documentUpdateDto, MultipartFile file,Long documentId) throws IOException {
        Optional<Document> result = documentRepository.findById(documentId);
        if (result.isPresent()) {
            Document document = DocumentMapper.INSTANCE.mapFromDocumentUpdateDto(documentUpdateDto);
            document.setId(result.get().getId());
            document.setApproved(true);
            document.setCreatedAt(result.get().getCreatedAt());
            document.setUpdatedAt(LocalDateTime.now());

            if(file !=null && !file.isEmpty()){
                if (result.get().getFilePath() != null) {
                    Files.deleteIfExists(Paths.get(result.get().getFilePath()));
                }
                String filePath = FOLDER_PATH + file.getOriginalFilename();
                document.setFilePath(filePath);
                document.setFileName(file.getOriginalFilename());
                file.transferTo(new File(filePath));
            }
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
