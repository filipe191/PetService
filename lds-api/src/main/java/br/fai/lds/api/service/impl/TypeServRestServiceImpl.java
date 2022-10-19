package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.TypeServRestService;
import br.fai.lds.db.dao.TypeServiceDao;
import br.fai.lds.model.entities.TypeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServRestServiceImpl implements TypeServRestService<TypeServiceModel> {

    @Autowired
    private TypeServiceDao typeServiceDao;
    @Override
    public List<TypeServiceModel> find() {
        return typeServiceDao.find();
    }

    @Override
    public TypeServiceModel findById(int id) {

        if (id < 0 ) return null;

        return (TypeServiceModel) typeServiceDao.findById(id);
    }

    @Override
    public int create(TypeServiceModel entity) {
        return typeServiceDao.create(entity);
    }

    @Override
    public boolean update(int id, TypeServiceModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return typeServiceDao.deleteById(id);
    }
}
