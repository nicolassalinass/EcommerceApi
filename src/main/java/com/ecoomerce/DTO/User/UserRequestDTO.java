package com.ecoomerce.DTO.User;

import java.time.LocalDateTime;

public record UserRequestDTO(
        String username,
        String email,
        String password,
        String rol,
        boolean isActive,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
