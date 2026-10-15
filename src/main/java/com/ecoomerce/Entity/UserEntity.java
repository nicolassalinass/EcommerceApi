package com.ecoomerce.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 60)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private boolean isActive;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
