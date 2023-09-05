package com.bcp.customer.management.web.mapper;

import com.bcp.customer.management.domain.DocumentType;
import com.bcp.customer.management.web.contracts.CreateDocumentTypeRequest;
import com.bcp.customer.management.web.contracts.DocumentTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring")
public interface DocumentTypeMapper {
	DocumentTypeMapper INSTANCE= Mappers.getMapper(DocumentTypeMapper.class);
	DocumentType createRequestToEntity(CreateDocumentTypeRequest source);
	
	DocumentTypeResponse entityToResponse(DocumentType source);
	@Mapping(target = "id", ignore = true)
	void update(@MappingTarget DocumentType entity, DocumentType updateEntity);
	
}
