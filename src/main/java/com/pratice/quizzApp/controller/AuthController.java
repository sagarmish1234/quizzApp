package com.pratice.quizzApp.controller;

import com.pratice.quizzApp.models.Role;
import com.pratice.quizzApp.models.RoleName;
import com.pratice.quizzApp.models.User;
import com.pratice.quizzApp.payload.ApiResponse;
import com.pratice.quizzApp.payload.JwtAuthenticationResponse;
import com.pratice.quizzApp.payload.LoginRequest;
import com.pratice.quizzApp.payload.SignUpRequest;
import com.pratice.quizzApp.repository.UserRepository;
import com.pratice.quizzApp.security.JwtTokenProvider;
import com.pratice.quizzApp.security.UserPrincipal;
import com.pratice.quizzApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );
            String jwt = tokenProvider.generateToken(authentication);
            User user = userService.getUserById(tokenProvider.getUserIdFromJWT(jwt));
            boolean isAdmin = false;
            for(Role i: user.getRoles()){
                if(i.getName().equals(RoleName.ROLE_ADMIN)){
                    isAdmin = true;
                    break;
                }
            }

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, isAdmin));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Bad Credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User result = userService.saveUser(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}