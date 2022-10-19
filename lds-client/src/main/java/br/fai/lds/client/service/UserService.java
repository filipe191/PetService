package br.fai.lds.client.service;

import br.fai.lds.model.entities.UserModel;

public interface UserService<T>  extends BaseService<T> {

    UserModel validateUserNameAndPassword(String username, String password);

}
