package com.ecoomerce.Services;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.DTO.User.UserUpdateDTO;
import com.ecoomerce.Entity.UserEntity;
import com.ecoomerce.Mappers.UserMapper;
import com.ecoomerce.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> getAll(){
        List<UserEntity> entities = this.userRepository.findAll();

        return this.userMapper.toDTO(entities);
    }

    public UserResponseDto getById(Long id){
        UserEntity entity = this.userRepository.findById(id).orElse(null);

        if (entity == null) return null;

        return this.userMapper.toDTO(entity);
    }

    @Transactional
    public UserResponseDto create(UserRequestDTO requestDTO){
        if (requestDTO == null) return null;
        UserEntity entity = this.userMapper.toEntity(requestDTO);

        return this.userMapper.toDTO(this.userRepository.save(entity));
    }

    @Transactional
    public UserResponseDto upadte(Long id, UserUpdateDTO updateDTO){
        UserEntity entity = this.userRepository.findById(id).orElse(null);

        if (entity == null) return null;
        this.userMapper.updateEntityFromDTO(entity, updateDTO);

        return this.userMapper.toDTO(this.userRepository.save(entity));
    }


    public void delete(Long id){
        UserEntity user = this.userRepository.findById(id).orElse(null);
        user.setActive(false);
        this.userRepository.save(user);

    }

    public boolean existUser(Long id){
        return this.userRepository.existsById(id);
    }




}
