package com.backgf.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import com.backgf.utils.TipoInvestimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_investimento")
public class Investimento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "Tipo não pode ser vazio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInvestimento tipo;
  
    @NotNull(message = "Valor não pode ser vazio")
    @Check(constraints = "valor > 0")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "Data não pode ser vazia")
    @Check(constraints = "data <= CURRENT_TIMESTAMP")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "A data deve ser anterior à data e hora atual")
    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Investimento() {
    }

    public Investimento(String nome, TipoInvestimento tipo, Double valor, LocalDateTime data, Usuario usuario) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoInvestimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInvestimento tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}