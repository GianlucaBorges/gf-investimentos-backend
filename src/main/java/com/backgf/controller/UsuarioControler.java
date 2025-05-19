package com.backgf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backgf.dto.UsuarioRequest;
import com.backgf.dto.UsuarioResponse;
import com.backgf.model.Usuario;
import com.backgf.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UsuarioControler {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequest req) {
        Usuario usuario = new Usuario();
        usuario.setNome(req.getNome());
        usuario.setEmail(req.getEmail());
        usuario.setSenha(req.getSenha());
        try {
            Usuario salvo = usuarioService.cadastrarUsuario(usuario);
            UsuarioResponse res =  new UsuarioResponse(salvo.getId(), salvo.getNome(), salvo.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuário cadastrado com sucesso", "usuario", res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Erro ao cadastrar usuário", "error", e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.listarUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar usuários: " + e.getMessage());
        }
    }
}
