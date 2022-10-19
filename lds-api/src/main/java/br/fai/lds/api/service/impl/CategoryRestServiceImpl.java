package br.fai.lds.api.service.impl;

import br.fai.lds.api.service.CategoryRestService;
import br.fai.lds.db.dao.CategoryDao;
import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.entities.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRestServiceImpl implements CategoryRestService<CategoryModel> {

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public List<CategoryModel> find() {
        return categoryDao.find();
    }

    @Override
    public CategoryModel findById(int id) {
        if (id < 0) return null;

        return (CategoryModel) categoryDao.findById(id);
    }

    @Override
    public int create(CategoryModel entity) {
        return categoryDao.create(entity);
    }

    @Override
    public boolean update(final int id, CategoryModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return categoryDao.deleteById(id);
    }
}
