package ua.goit.service;

import ua.goit.dao.CategoryDao;
import ua.goit.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void add(Category entity) {
        categoryDao.add(entity);
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public void update(Category entity) {
        categoryDao.update(entity);
    }

    @Override
    public void remove(Integer id) {
        categoryDao.remove(id);
    }
}
