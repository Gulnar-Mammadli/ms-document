package com.group11.msdocument.repository;

import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    Optional<Document> findByCustomerIdAndLoanIdAndDocumentType(Long customerId, Long loanId, DocumentType documentType);
    Optional<List<Document>> findAllByCustomerId(Long customerId);

}
