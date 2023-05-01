package com.group11.msdocument.mapper;

import com.group11.msdocument.model.Document;
import com.group11.msdocument.model.dto.DocumentDto;
import com.group11.msdocument.model.dto.DocumentUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);
    Document mapToDocument(DocumentDto documentDto);

    Document mapFromDocumentUpdateDto(DocumentUpdateDto documentUpdateDto);
}
