package br.fai.lds.db.dao;

import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.entities.BaseEntity;

public interface AnnouncementDao<T> extends BaseDao<T>{
    int create(AnnouncementModel entity);

    boolean update(AnnouncementModel entity);
}
