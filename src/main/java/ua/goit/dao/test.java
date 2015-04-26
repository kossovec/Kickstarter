package ua.goit.dao;

import ua.goit.model.Category;
import ua.goit.service.CategoryService;
import ua.goit.service.CategoryServiceImpl;

public class test {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());
//        categoryService.update(new Category(1, "Art", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())));
//        categoryService.update(new Category(2, "IT", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())));
//        categoryService.update(new Category(3, "Space", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())));
//        categoryService.update(new Category(4, "Games", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())));
        for (Category c : categoryService.getAll()) {
            System.out.println(c.getCategoryName());
        }
    }
}
