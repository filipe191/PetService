package br.fai.lds.model.entities;

import java.sql.Timestamp;

public class ShopModel<T> extends BaseEntity<T> {

    private String quantidade;

    private Timestamp dataHora;

    private String situacao;

    private ShopModel request;

    private AnnouncementModel announcementModel;

    private UserModel userModel;

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public ShopModel getRequest() {
        return request;
    }

    public void setRequest(ShopModel request) {
        this.request = request;
    }

    public AnnouncementModel getAnnouncementModel() {
        return announcementModel;
    }

    public void setAnnouncementModel(AnnouncementModel announcementModel) {
        this.announcementModel = announcementModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
