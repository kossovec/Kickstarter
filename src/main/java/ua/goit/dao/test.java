package ua.goit.dao;

import ua.goit.model.Category;

public class test {
  public static void main(String[] args) {
//    Category category = new Category("Category 5");
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
//    categoryDao.add(category);
//
//    for (Category category: categoryDao.getAll()) {
//      System.out.println(category.getCategoryName());
//    }
//    System.out.println(categoryDao.getById(3).getCategoryName());
    categoryDao.remove(1);

    for (Category category: categoryDao.getAll()) {
      System.out.println(category.getCategoryName());
    }

  }
}
