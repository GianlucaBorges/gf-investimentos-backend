package com.backgf.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.backgf.model.Investimento;
import com.backgf.utils.TipoInvestimento;

public class InvestimentoSpecifications {
    
    public static Specification<Investimento> hasTipoInvestimento(TipoInvestimento tipo) {
        return (root, query, cb) -> {
            System.out.println("Tipo recebido na specification: " + tipo);
            System.out.println("Tipo do atributo: " + root.getModel().getAttribute("tipo"));
            
            return tipo == null ? cb.conjunction() : cb.equal(root.get("tipo"), tipo);
        };
    }

    public static Specification<Investimento> hasDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, cb) -> {
            if (startDate != null && endDate != null) {
                return cb.between(root.get("data"), startDate, endDate);
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("data"), startDate);
            } else if (endDate != null) {
                return cb.lessThanOrEqualTo(root.get("data"), endDate);
            }
            return cb.conjunction();
        };
    }

    public static Specification<Investimento> hasUsuarioId(Long usuarioId) {
        return (root, query, cb) -> usuarioId == null ? cb.conjunction() : cb.equal(root.get("usuario").get("id"), usuarioId);
    }
}
