package br.fai.lds.model.entities;

public class CityModel<T> extends BaseEntity<T> {

    private String cidade;

    private String UF;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
}
