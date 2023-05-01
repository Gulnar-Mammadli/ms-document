package com.group11.msdocument.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdocument.model.enums.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentDto {

    @JsonProperty("loan_id")
    private Long loanId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("approved_by")
    private Long approvedBy;

    @JsonProperty("document_type")
    private DocumentType documentType;

}
