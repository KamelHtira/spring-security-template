package com.SpringBootApplicationTemplate.demo.controller;

import com.SpringBootApplicationTemplate.demo.DTOs.Request;
import com.SpringBootApplicationTemplate.demo.DTOs.Response;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.AuthenticationRequestDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.AuthenticationResponseDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.RegisterRequestDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.RegisterResponseDTO;
import com.SpringBootApplicationTemplate.demo.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<Response<RegisterResponseDTO>> register(
            @RequestBody RegisterRequestDTO request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Response<AuthenticationResponseDTO>> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    @PostMapping("/protected")
    public ResponseEntity<String> protectedEndPoint(){
        return ResponseEntity.ok("Hurraa you have a token :)");
    }
}
