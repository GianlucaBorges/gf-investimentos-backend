package com.backgf.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backgf.dto.InvestimentoRequest;
import com.backgf.dto.InvestimentoResponse;
import com.backgf.dto.PaginatedResponse;
import com.backgf.model.Investimento;
import com.backgf.model.Usuario;
import com.backgf.service.InvestimentoService;
import com.backgf.service.UsuarioService;
import com.backgf.utils.TipoInvestimento;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investimento")
public class InvestimentoController {
    
    @Autowired
    private InvestimentoService investimentoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarInvestimento(@RequestBody @Valid InvestimentoRequest req) {
        if (req.getValor() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Valor deve ser maior que zero", "error", "Valor inválido: " + req.getValor()));
        }
        Investimento investimento = new Investimento();
        investimento.setTipo(req.getTipo());
        investimento.setValor(req.getValor());
        investimento.setData(investimentoService.parseDate(req.getData()));
        investimento.setNome(req.getNome());

        Optional<Usuario> usuario = usuarioService.buscarPorId(req.getUsuarioId());
        if (usuario.isPresent()) {
            investimento.setUsuario(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Usuário não encontrado", "error", "Usuário com ID " + req.getUsuarioId() + " não encontrado"));
        }
        investimento.setUsuario(usuario.get());
        try {
            Investimento salvo = investimentoService.cadastrarInvestimento(investimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Investimento cadastrado com sucesso", "investimento", salvo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Erro ao cadastrar investimento", "error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInvestimentoPorId(@PathVariable Long id) {
        Optional<Investimento> investimento = investimentoService.buscarInvestimentoPorId(id);
        if (investimento.isPresent()) {
            return ResponseEntity.ok(investimento.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Investimento não encontrado", "error", "Investimento com ID " + id + " não encontrado"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInvestimento(@PathVariable Long id, @RequestBody @Valid InvestimentoRequest req) {
        Optional<Investimento> investimentoOpt = investimentoService.buscarInvestimentoPorId(id);
        if (investimentoOpt.isPresent()) {
            Investimento investimento = investimentoOpt.get();
            investimento.setTipo(req.getTipo());
            investimento.setValor(req.getValor());
            investimento.setData(investimentoService.parseDate(req.getData()));
            investimento.setNome(req.getNome());

            Optional<Usuario> usuario = usuarioService.buscarPorId(req.getUsuarioId());
            if (usuario.isPresent()) {
                investimento.setUsuario(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Usuário não encontrado", "error", "Usuário com ID " + req.getUsuarioId() + " não encontrado"));
            }
            try {
                Investimento salvo = investimentoService.atualizarInvestimento(investimento);
                return ResponseEntity.ok(Map.of("message", "Investimento atualizado com sucesso", "investimento", salvo));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Erro ao atualizar investimento", "error", "Erro ao atualizar investimento: " + e.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Investimento não encontrado", "error", "Investimento com ID " + id + " não encontrado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarInvestimento(@PathVariable Long id) {
        Optional<Investimento> investimento = investimentoService.buscarInvestimentoPorId(id);
        if (investimento.isPresent()) {
            investimentoService.deleteInvestimentoById(id);
            return ResponseEntity.ok(Map.of("message", "Investimento deletado com sucesso", "investimento", investimento.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Investimento não encontrado", "error", "Investimento com ID " + id + " não encontrado"));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarInvestimentos(
        @RequestParam(required = true) Long usuarioId,
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {        
        TipoInvestimento tipoEnum = null;
        if (tipo != null) {
            try {
                tipoEnum = TipoInvestimento.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                    .body(Map.of(
                        "message", "Tipo de investimento inválido",
                        "valores_aceitos", Arrays.toString(TipoInvestimento.values())
                    ));
            }
        }
        try {
            Page<Investimento> investimentos = investimentoService.listarInvestimentos(
                usuarioId,
                tipoEnum,
                startDate,
                endDate,
                pageable
            );
            Page<InvestimentoResponse> investimentoResponse = investimentos.map(investimento -> {
                return new InvestimentoResponse(
                    investimento.getId(),
                    investimento.getNome(),
                    investimento.getValor(),
                    investimento.getData().toString(),
                    investimento.getTipo(),
                    investimento.getUsuario().getId()
                );
            });

            PaginatedResponse<InvestimentoResponse> paginatedResponse = new PaginatedResponse<>(investimentoResponse);
            return ResponseEntity.ok(paginatedResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    Map.of(
                        "message",
                        "Erro ao listar investimentos",
                        "error",
                        e.getMessage()
                    )
                );
        }
    }
}