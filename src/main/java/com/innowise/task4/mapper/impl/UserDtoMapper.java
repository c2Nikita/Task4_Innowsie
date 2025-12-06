package com.innowise.task4.mapper.impl;

import com.innowise.task4.mapper.DtoMapper;
import com.innowise.task4.model.User;
import com.innowise.task4.dto.UserDto;

public class UserDtoMapper implements DtoMapper<User, UserDto> {

    @Override
    public UserDto map(User user) {
        return new UserDto(
              user.getId(),
              user.getName(),
              user.getRole()
        );
    }
}
