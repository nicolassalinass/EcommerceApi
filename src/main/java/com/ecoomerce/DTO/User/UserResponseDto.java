package com.ecoomerce.DTO.User;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String rol,
        boolean isActive,
        LocalDateTime createAt,
        LocalDateTime updateAt

) {
}
