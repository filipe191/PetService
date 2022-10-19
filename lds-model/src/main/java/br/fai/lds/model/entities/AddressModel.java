package br.fai.lds.model.entities;

public class AddressModel<T> extends BaseEntity<T> {

    private String logradouro;
    private int numero;
    private int CEP;

    private CityModel cityModel;



    private UserModel userModel;

    private String Bairro;
    // posso colocar o municipio tudo no endereço


    public String getLogradouro() {
        return logradouro;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }


    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }
}
