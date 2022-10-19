package br.fai.lds.client.service;

import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.entities.BaseEntity;

public interface AnnouncementService<T> extends BaseService<T> {

    AnnouncementModel validateQuantidade(String quantidade);
}
