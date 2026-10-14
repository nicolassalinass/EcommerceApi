package com.ecoomerce.Entity;

import java.time.LocalDateTime;

public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String rol;
    private boolean isActive;
    private LocalDateTime createAt;
}
