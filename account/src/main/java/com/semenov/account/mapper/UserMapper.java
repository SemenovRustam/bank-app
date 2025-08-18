package com.semenov.account.mapper;

import com.semenov.account.dto.UserDto;
import com.semenov.account.entity.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    UserDto toUserDto(User item);
}
