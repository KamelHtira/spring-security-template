package com.SpringBootApplicationTemplate.demo.service;

import com.SpringBootApplicationTemplate.demo.DTOs.Response;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.AuthenticationRequestDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.AuthenticationResponseDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.RegisterRequestDTO;
import com.SpringBootApplicationTemplate.demo.DTOs.templates.RegisterResponseDTO;
import com.SpringBootApplicationTemplate.demo.model.Role;
import com.SpringBootApplicationTemplate.demo.model.User;
import com.SpringBootApplicationTemplate.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response register( RegisterRequestDTO request){
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return Response.builder()
                .data(RegisterResponseDTO.builder()
                        .token(jwtToken)
                        .build())
                .success(true)
                .message("User create successfully")
                .httpStatusCode(HttpStatusCode.valueOf(201))
                .build();
    }

    public Response authenticate(AuthenticationRequestDTO request ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )

        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return Response.builder()
                .data(AuthenticationResponseDTO.builder()
                        .token(jwtToken)
                        .build())
                .success(true)
                .message("Authentication successful")
                .httpStatusCode(HttpStatusCode.valueOf(200))
                .build();
    }
}
