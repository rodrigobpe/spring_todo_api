package com.example.apiTodoList.controller;

import com.example.apiTodoList.dto.CreateUserDTO;
import com.example.apiTodoList.dto.LoginDTO;
import com.example.apiTodoList.dto.ResponseLoginDTO;
import com.example.apiTodoList.dto.VerifyTokenDTO;
import com.example.apiTodoList.exception.UnauthorizedException;
import com.example.apiTodoList.model.User;
import com.example.apiTodoList.service.TokenService;
import com.example.apiTodoList.service.UserService;
import com.example.apiTodoList.utils.HandleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @CrossOrigin(value = "")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO body) {
        User user = this.userService.getByEmail(body.email());
        if (!passwordEncoder.matches(body.password(), user.getPassword())) throw new UnauthorizedException();
        String token = this.tokenService.generateToken(user);
        return new HandleResponse(new ResponseLoginDTO(user.getId(), user.getEmail(), user.getName(), token), HttpStatus.CREATED).run();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody CreateUserDTO body) {
        User newUser = userService.save(body);
        HandleResponse response = new HandleResponse(newUser, HttpStatus.OK);
        return response.run();
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verify(@RequestBody VerifyTokenDTO body){
        Boolean tokenIsValid = this.userService.verifyToken(this.tokenService.validateToken(body.token()));
        return new HandleResponse(tokenIsValid, HttpStatus.OK).run();
     }
}
