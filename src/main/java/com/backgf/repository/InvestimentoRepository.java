package com.backgf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.backgf.model.Investimento;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long>,
                                                JpaSpecificationExecutor<Investimento> {

}