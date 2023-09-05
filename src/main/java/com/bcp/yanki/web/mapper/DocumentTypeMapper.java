package com.bcp.yanki.web.mapper;

import com.bcp.yanki.domain.DocumentType;
import com.bcp.yanki.web.contracts.CreateDocumentTypeRequest;
import com.bcp.yanki.web.contracts.DocumentTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {
    DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);

    DocumentType createRequestToEntity(CreateDocumentTypeRequest source);

    DocumentTypeResponse entityToResponse(DocumentType source);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget DocumentType entity, DocumentType updateEntity);

}
