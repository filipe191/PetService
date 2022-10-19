package br.fai.lds.db.dao;

import br.fai.lds.model.entities.UserModel;

public interface UserDao<T> extends BaseDao<T>{

    UserModel validateUserNameAndPassword(String username, String password);


}
