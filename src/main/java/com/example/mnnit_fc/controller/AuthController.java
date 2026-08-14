package com.example.mnnit_fc.controller;

import com.example.mnnit_fc.controller.dto.FullNameResponseDTO;
import com.example.mnnit_fc.controller.dto.LoginRequestDTO;
import com.example.mnnit_fc.controller.dto.LoginResponseDTO;
import com.example.mnnit_fc.service.AuthenticationService;
import com.example.mnnit_fc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<FullNameResponseDTO> me(){
        return ResponseEntity.ok(
                userService.getUserFullName()
        );
    }


}