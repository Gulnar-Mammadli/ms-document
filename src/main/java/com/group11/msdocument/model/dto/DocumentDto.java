package com.group11.msdocument.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdocument.model.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentDto {

    @NotNull
    @JsonProperty("loan_id")
    private Long loanId;

    @NotNull
    @JsonProperty("customer_id")
    private Long customerId;

    @NotNull
    @JsonProperty("document_type")
    private DocumentType documentType;

}
