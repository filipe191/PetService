package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.AnnouncementRestService;
import br.fai.lds.db.dao.AnnouncementDao;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.entities.AnnouncementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncimentoRestServiceImpl implements AnnouncementRestService<AnnouncementModel> {

    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public List<AnnouncementModel> find() {
        return announcementDao.find();
    }

    @Override
    public AnnouncementModel findById(int id) {
        if (id < 0) return null;

        return (AnnouncementModel) announcementDao.findById(id);
    }

    @Override
    public int create(AnnouncementModel entity) {
        return announcementDao.create(entity);
    }

    @Override
    public boolean update(int id, AnnouncementModel entity) {
        return false;
    }

//    @Override   verificar com o rodolfo amanha como implemento isso
//    public boolean update(final int id ,AnnouncementModel entity) {
//        AnnouncementModel user = (AnnouncementModel) announcementDao.findById(id);
//        if (user == null) return false;
//
//        user.setEmail(entity.getEmail());
//        user.setFullname(entity.getFullname());
//
//        //colocar validacao
//        return announcementDao.update(user);
//        return 0;
//    }

    @Override
    public boolean deleteById(int id) {
        return announcementDao.deleteById(id);
    }
}
