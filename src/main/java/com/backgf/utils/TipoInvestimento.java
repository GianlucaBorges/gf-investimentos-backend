package com.backgf.utils;

public enum TipoInvestimento {
  ACAO("Ação"),
  TITULO("Título"),
  FUNDO("Fundo");

  private String descricao;

  TipoInvestimento(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
  
  public static TipoInvestimento fromDescricao(String descricao) {
    for (TipoInvestimento tipo : TipoInvestimento.values()) {
      if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
        return tipo;
      }
    }
    throw new IllegalArgumentException("Tipo de investimento inválido: " + descricao);
  }
}
