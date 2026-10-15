package com.ecoomerce.Services;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.Entity.UserEntity;
import com.ecoomerce.Mappers.UserMapper;
import com.ecoomerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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




}
