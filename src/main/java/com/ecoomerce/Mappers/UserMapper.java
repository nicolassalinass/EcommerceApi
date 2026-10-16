package com.ecoomerce.Mappers;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.DTO.User.UserUpdateDTO;
import com.ecoomerce.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

        return userEntity;
    }

    public void updateEntityFromDTO(UserEntity entity, UserUpdateDTO updateDTO){
        entity.setName(updateDTO.username());
        entity.setEmail(updateDTO.email());
        entity.setRol(updateDTO.rol());
        entity.setActive(updateDTO.isActive());
    }



}
