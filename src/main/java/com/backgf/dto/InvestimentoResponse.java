package com.backgf.dto;

import com.backgf.utils.TipoInvestimento;

public class InvestimentoResponse {
    private Long id;
    private String nome;
    private Double valor;
    private String data;
    private TipoInvestimento tipo;
    private Long usuarioId;

    public InvestimentoResponse() {
    }

    public InvestimentoResponse(Long id, String nome, Double valor, String data, TipoInvestimento tipo, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.usuarioId = usuarioId;;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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