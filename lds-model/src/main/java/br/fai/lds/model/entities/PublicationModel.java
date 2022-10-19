package br.fai.lds.model.entities;

public class PublicationModel<T> extends BaseEntity<T> {


    private String titulo;

    private String descricao;

    private String endereco;

    private String localEnvento;

    private UserModel userModel;

    public String getLocalEnvento() {
        return localEnvento;
    }

    public void setLocalEnvento(String localEnvento) {
        this.localEnvento = localEnvento;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
