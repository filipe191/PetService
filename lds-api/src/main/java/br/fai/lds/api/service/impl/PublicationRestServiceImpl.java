package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.PublicationRestService;
import br.fai.lds.db.dao.PublicationDao;
import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.entities.PublicationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationRestServiceImpl implements PublicationRestService<PublicationModel> {

    @Autowired
    private PublicationDao publicationDao;

    @Override
    public List<PublicationModel> find() {
        return publicationDao.find();
    }

    @Override
    public PublicationModel findById(int id) {
        if (id < 0) return null;

        return (PublicationModel) publicationDao.findById(id);
    }

    @Override
    public int create(PublicationModel entity) {
        return publicationDao.create(entity);
    }

    @Override
    public boolean update(final int id, PublicationModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return publicationDao.deleteById(id);
    }
}
