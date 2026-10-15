package com.ecoomerce.Mappers;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDto toDTO(UserEntity userEntity){
        return new UserResponseDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getRol(),
                userEntity.isActive(),
                userEntity.getCreateAt(),
                userEntity.getUpdateAt()
        );
    }

    public List<UserResponseDto> toDTO(List<UserEntity> userEntities){
        return userEntities.stream().map(this::toDTO).toList();
    }

    public UserEntity toEntity(UserRequestDTO requestDTO){
        UserEntity userEntity = new UserEntity();

        userEntity.setName(requestDTO.username());
        userEntity.setEmail(requestDTO.email());
        userEntity.setPassword(requestDTO.password());
        userEntity.setRol(requestDTO.rol());
        userEntity.setActive(requestDTO.isActive());
        userEntity.setCreateAt(requestDTO.createAt());
        userEntity.setUpdateAt(requestDTO.updateAt());

        return userEntity;
    }



}
