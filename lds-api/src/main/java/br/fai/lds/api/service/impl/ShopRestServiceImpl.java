package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.ShopRestService;
import br.fai.lds.db.dao.ShopDao;
import br.fai.lds.model.entities.ShopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopRestServiceImpl implements ShopRestService<ShopModel> {

    @Autowired
    private ShopDao shopDao;

    @Override
    public List find() {
        return shopDao.find();
    }

    @Override
    public ShopModel findById(int id) {
        if (id < 0) return null;

        return (ShopModel) shopDao.findById(id);
    }

    @Override
    public int create(ShopModel entity) {
        return 0;
    }

    @Override
    public boolean update(int id, ShopModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return shopDao.deleteById(id);
    }
}
