package br.fai.lds.model.entities;

import java.sql.Timestamp;

public class BaseEntity<T> {


    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
