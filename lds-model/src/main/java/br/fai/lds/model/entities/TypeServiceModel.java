package br.fai.lds.model.entities;

public class TypeServiceModel<T> extends BaseEntity<T> {

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
