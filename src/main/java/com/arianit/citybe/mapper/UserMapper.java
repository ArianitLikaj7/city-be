package com.arianit.citybe.mapper;

import com.arianit.citybe.dto.UserDto;
import com.arianit.citybe.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto mapEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    public User mapDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}