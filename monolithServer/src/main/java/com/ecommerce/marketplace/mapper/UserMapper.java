package com.ecommerce.marketplace.mapper;

import com.ecommerce.marketplace.DTO.UserDTO;
import com.ecommerce.marketplace.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDto(User user);

    List<UserDTO> usersToUserDtos(List<User> users);

    User userDtoToUser(UserDTO userDTO);
}
