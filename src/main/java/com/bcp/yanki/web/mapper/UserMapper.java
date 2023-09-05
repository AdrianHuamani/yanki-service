package com.bcp.yanki.web.mapper;

import com.bcp.yanki.domain.User;
import com.bcp.yanki.web.contracts.CreateUserRequest;
import com.bcp.yanki.web.contracts.UpdateUserRequest;
import com.bcp.yanki.web.contracts.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User createRequestToEntity(CreateUserRequest source);

    User updateRequestToEntity(UpdateUserRequest source);

    @Mapping(target = "documentTypeDescription", source = "source.documentType.description")
    UserResponse entityToResponse(User source);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget User entity, User updateEntity);

}
