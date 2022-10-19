package br.fai.lds.db.dao;

import java.util.List;

//Dao = data access object
public interface BaseDao<T> {

    List<T> find();

    T findById(int id);

    int create(T entity);

    boolean update(T entity);

    boolean deleteById(int id);
}
