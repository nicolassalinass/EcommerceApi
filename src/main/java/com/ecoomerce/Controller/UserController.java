package com.ecoomerce.Controller;

import com.ecoomerce.DTO.User.UserRequestDTO;
import com.ecoomerce.DTO.User.UserResponseDto;
import com.ecoomerce.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@Valid @PathVariable long id){
        UserResponseDto user = this.userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDTO requestDTO){
        UserResponseDto request = this.userService.create(requestDTO);

        if (request == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

}
