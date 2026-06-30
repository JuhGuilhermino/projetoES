package com.example.application1.service;

import com.example.application1.dto.LoginRequestDTO;
import com.example.application1.dto.UserRegisterRequestDTO;
import com.example.application1.dto.UserRegisterResponseDTO;
import com.example.application1.dto.UserResponseDTO;
import com.example.application1.model.User;
import com.example.application1.repository.UserRepository;
import org.springframework.stereotype.Service;
//import java.util.Optional;

@Service
public class UserService { // Interage com a inteface do Repository para realizar a persistência dos dados
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDTO register(UserRegisterRequestDTO request) {
        // 1. Validações de Negócio
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Nome de usuário já está em uso.");
        }

        // 2. Criação da Entidade e Criptografia da Senha
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); 
        user.setCurrentLevel(request.getLevel());

        // 3. Persistência
        User savedUser = userRepository.save(user);

        // 4. Retorno do DTO Limpo
        return new UserRegisterResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCurrentLevel()
        );
    }

    public UserResponseDTO login(LoginRequestDTO request) {
        // 1. Busca o usuário pelo e-mail
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas (E-mail ou senha incorretos)."));

        // 2. Valida a senha usando BCrypt
        if (request.getPassword() == user.getPassword()) {
            throw new IllegalArgumentException("Credenciais inválidas (E-mail ou senha incorretos).");
        }

        // 3. Retorna os dados do usuário autenticado
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCurrentLevel()
        );
    }
}
