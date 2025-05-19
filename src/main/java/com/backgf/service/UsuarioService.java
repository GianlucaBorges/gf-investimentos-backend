package com.backgf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backgf.model.Usuario;
import com.backgf.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario usuario) {
        String email = usuario.getEmail();
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado.");
        }

        String senha = usuario.getSenha();

        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(senha));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        String email = usuario.getEmail();
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado.");
        }

        String senha = usuario.getSenha();
        if (senha != null && !senha.isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(senha));
        }
        
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
