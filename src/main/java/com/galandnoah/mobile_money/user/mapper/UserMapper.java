package com.galandnoah.mobile_money.user.mapper;

import com.galandnoah.mobile_money.user.dto.CreateUser;
import com.galandnoah.mobile_money.user.dto.UserResponse;
import com.galandnoah.mobile_money.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * to UserEntity
     * @param createUser user's creation data
     * @return created user's data
     * */
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(CreateUser createUser);

    /**
     * to UserResponse
     * @param user user's data
     * @return user dto's data
     * */
    @Mapping(target = "createdAt", source = "createdAt")
    UserResponse toDTO(User user);
}
