package com.ecoomerce.DTO.User;

import java.time.LocalDateTime;

public record UserUpdateDTO(
        String username,
        String email,
        String rol,
        boolean isActive
) {
}
