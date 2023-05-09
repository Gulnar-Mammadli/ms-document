package com.group11.msdocument.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdocument.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("loan_id")
    @Column(nullable = false,name = "loan_id")
    private Long loanId;

    @JsonProperty("customer_id")
    @Column(nullable = false,name = "customer_id")
    private Long customerId;

    @JsonProperty("is_approved")
    @Column(name = "is_approved")
    private boolean isApproved = false;

    @JsonProperty("approved_by")
    @Column(name = "approved_by")
    private Long approvedBy;

    @JsonProperty("document_type")
    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String fileName;

    private String filePath;


}
