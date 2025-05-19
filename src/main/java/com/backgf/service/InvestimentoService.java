package com.backgf.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backgf.model.Investimento;
import com.backgf.repository.InvestimentoRepository;
import com.backgf.repository.InvestimentoSpecifications;
import com.backgf.utils.TipoInvestimento;

@Service
public class InvestimentoService {
    
    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Investimento cadastrarInvestimento(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }

    public Optional<Investimento> buscarInvestimentoPorId(Long id) {
        return investimentoRepository.findById(id);
    }

    public Page<Investimento> listarInvestimentos(
        Long usuarioId,
        TipoInvestimento tipo,
        String startDate,
        String endDate,
        Pageable pageable
    ) {
        LocalDateTime start = parseDate(startDate);
        LocalDateTime end = parseDate(endDate);

        Specification<Investimento> spec = Specification
            .where(InvestimentoSpecifications.hasUsuarioId(usuarioId));
        
        if (tipo != null) {
            spec = spec.and(InvestimentoSpecifications.hasTipoInvestimento(tipo));
        }

        if (start != null || end != null) {
            spec = spec.and(InvestimentoSpecifications.hasDateRange(start, end));
        }

        // Specification<Investimento> spec = Specification
        //     .where(InvestimentoSpecifications.hasDateRange(start, end))
        //     .and(InvestimentoSpecifications.hasUsuarioId(usuarioId))
        //     .and(InvestimentoSpecifications.hasTipoInvestimento(tipo));
        return investimentoRepository.findAll(spec, pageable);
    }

    public Investimento atualizarInvestimento(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }

    public void deleteInvestimentoById(Long id) {
        investimentoRepository.deleteById(id);
    }

    public LocalDateTime parseDate(String date) {
        try {
            return (date != null && !date.isBlank()) ? LocalDateTime.parse(date) : null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Data inválida: " + date, e);
        }
    }
}