package com.backgf.dto;

import com.backgf.utils.TipoInvestimento;

public class InvestimentoRequest {
    private String nome;
    private Double valor;
    private String data;
    private TipoInvestimento tipo;
    private Long usuarioId;

    public InvestimentoRequest() {
    }

    public InvestimentoRequest(String nome, Double valor, String data, TipoInvestimento tipo, Long usuarioId) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TipoInvestimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInvestimento tipo) {
        this.tipo = tipo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}