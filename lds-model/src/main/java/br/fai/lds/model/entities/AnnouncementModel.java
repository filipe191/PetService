package br.fai.lds.model.entities;

import br.fai.lds.model.enums.ServiceType;

import java.util.List;

public class AnnouncementModel<T> extends BaseEntity<T> {

    private String descricao;
    private String titulo;

    private ServiceType tipo;
    private String preco;

    private CategoryModel categoryModel;

    private BaseEntity<T> baseEntity;

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ServiceType getTipo() {
        return tipo;
    }

    public void setTipo(ServiceType tipo) {
        this.tipo = tipo;
    }

    public BaseEntity<T> getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity<T> baseEntity) {
        this.baseEntity = baseEntity;
    }

    public br.fai.lds.model.entities.CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(br.fai.lds.model.entities.CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

}
