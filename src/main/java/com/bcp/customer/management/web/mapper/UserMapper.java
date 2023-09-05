package com.bcp.customer.management.web.mapper;

import com.bcp.customer.management.domain.User;
import com.bcp.customer.management.web.contracts.CreateUserRequest;
import com.bcp.customer.management.web.contracts.UserResponse;
import com.bcp.customer.management.web.contracts.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring")
public interface UserMapper {
	UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
	User createRequestToEntity(CreateUserRequest source);
	User updateRequestToEntity(UpdateUserRequest source);
	@Mapping(target="documentTypeDescription", source="source.documentType.description")
	UserResponse entityToResponse(User source);
	@Mapping(target = "id", ignore = true)
	void update(@MappingTarget User entity, User updateEntity);
	
}
