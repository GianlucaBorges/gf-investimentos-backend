package com.backgf.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backgf.dto.AuthRequest;
import com.backgf.dto.AuthResponse;
import com.backgf.model.Usuario;
import com.backgf.repository.UsuarioRepository;
import com.backgf.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest authRequest) {
        String email = authRequest.getEmail();
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && passwordEncoder.matches(authRequest.getSenha(), usuario.get().getSenha())) {
            String token = jwtUtil.gerarToken(email);
            return new AuthResponse(usuario.get().getId(), usuario.get().getNome(), email, token);
        }
        throw new RuntimeException("Email ou senha inválidos.");
    }
}