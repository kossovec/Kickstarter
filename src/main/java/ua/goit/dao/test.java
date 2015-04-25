package ua.goit.dao;

import ua.goit.model.Category;
import ua.goit.service.CategoryService;
import ua.goit.service.CategoryServiceImpl;

public class test {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());
//        categoryService.add(new Category(1, "Art", null));
//        categoryService.add(new Category(2, "IT", null));
//        categoryService.add(new Category(3, "Space", null));
//        categoryService.add(new Category(4, "Games", null));
        for (Category c : categoryService.getAll()) {
            System.out.println(c.getCategoryName());
        }

    }
}
