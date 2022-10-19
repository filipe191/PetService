package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.AddressRestService;
import br.fai.lds.db.dao.AddressDao;
import br.fai.lds.model.entities.AddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressRestServiceImpl implements AddressRestService<AddressModel> {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<AddressModel> find() {
        return addressDao.find();
    }

    @Override
    public AddressModel findById(int id) {
        if (id < 0) return null;

        return (AddressModel) addressDao.findById(id);
    }

    @Override
    public int create(AddressModel entity) {
        return addressDao.create(entity);
    }

    @Override
    public boolean update(int id, AddressModel entity) {
        AddressModel user = (AddressModel) addressDao.findById(id);
        if (user == null) return false;
        //colocar sempre oque eu irei atualizar
//        user.setEmail(entity.getEmail());
//        user.setFullname(entity.getFullname());

        //colocar validacao
        return addressDao.update(user);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
