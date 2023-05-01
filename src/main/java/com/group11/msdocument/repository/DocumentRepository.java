package com.group11.msdocument.repository;

import com.group11.msdocument.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    Optional<Document> findByCustomerIdAndLoanId(Long customerId, Long loanId);
}
