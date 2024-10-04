package com.example.apiTodoList.service;

import com.example.apiTodoList.dto.CreateUserDTO;
import com.example.apiTodoList.exception.BadRequestException;
import com.example.apiTodoList.exception.UserNotFoundException;
import com.example.apiTodoList.model.User;
import com.example.apiTodoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(CreateUserDTO user) {
        Optional<User> userExist = userRepository.findByEmail(user.email());
        if (userExist.isPresent()) throw new BadRequestException("Usuário já está cadastrado");

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.password()));
        newUser.setEmail(user.email());
        newUser.setName(user.name());
        return userRepository.save(newUser);
    }

    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getByEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean verifyToken(String email){
        Optional<User> user = this.userRepository.findByEmail(email);
        return user.isPresent();
    }

}
