package com.ecoomerce.Controller;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.DTO.User.UserUpdateDTO;
import com.ecoomerce.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuarios", description = "Endpoint de gestion de usuarios")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Obtiene una lista de Usuarios")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un usuario por id")
    public ResponseEntity<UserResponseDto> getById(@Valid @PathVariable long id){
        if (this.userService.existUser(id)){
            UserResponseDto user = this.userService.getById(id);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @Operation(summary = "Crea un nuevo usuario")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDTO requestDTO){
        UserResponseDto request = this.userService.create(requestDTO);

        if (request == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @PutMapping()
    @Operation(summary = "Actualiza un usuario sin contraseña")
    public ResponseEntity<UserResponseDto> update(@Valid Long id, @RequestBody UserUpdateDTO updateDTO){
        if (id == null || updateDTO == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.upadte(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        if (this.userService.existUser(id)){
            this.userService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
