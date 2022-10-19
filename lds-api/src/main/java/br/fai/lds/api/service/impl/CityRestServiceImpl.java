package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.CityRestService;
import br.fai.lds.db.dao.CityDao;
import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.entities.CityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityRestServiceImpl implements CityRestService<CityModel> {

    @Autowired
    private CityDao cityDao;


    @Override
    public List<CityModel> find() {
        return cityDao.find();
    }

    @Override
    public CityModel findById(int id) {
        if (id < 0) return null;

        return (CityModel) cityDao.findById(id);
    }

    @Override
    public int create(CityModel entity) {
        return cityDao.create(entity);
    }

    @Override
    public boolean update(final int id, CityModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return cityDao.deleteById(id);
    }
}
