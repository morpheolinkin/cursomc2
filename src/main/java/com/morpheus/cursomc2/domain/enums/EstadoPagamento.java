package com.morpheus.cursomc2.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private final Integer cod;
    private final String descricao;

    EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if (cod == null) {
            return null;
        }

        for (EstadoPagamento x :
                EstadoPagamento.values()) {
            return x;
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
