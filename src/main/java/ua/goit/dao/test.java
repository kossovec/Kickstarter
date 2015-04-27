package ua.goit.dao;

import ua.goit.model.Project;
import ua.goit.service.ProjectService;
import ua.goit.service.ProjectServiceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class test {
<<<<<<< HEAD
  public static void main(String[] args) {
//    Category category = new Category("Category 5");
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
//    categoryDao.add(category);
//
//    for (Category category: categoryDao.getAll()) {
//      System.out.println(category.getCategoryName());
//    }
//    System.out.println(categoryDao.getById(3).getCategoryName());
//    categoryDao.remove(1);
=======
    public static void main(String[] args) {
        Timestamp current = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
>>>>>>> 57ee57ac1765ce844777ffd09e750dd70873c054

//        UserService userService = new UserServiceImpl(new UserDaoImpl());
//        User alex = new User(1, "alex", "alex", "kickstarter", 12345, current);
//        userService.add(alex);
//        User alex = userService.getById(1);
//
//        CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());
//        Category art = categoryService.getById(5);
//        Category it = categoryService.getById(6);
//        Category space = categoryService.getById(7);
//        Category games = categoryService.getById(8);
//
//        ProjectService projectService = new ProjectServiceImpl(new ProjectDaoImpl());
//        projectService.add(new Project(1, "artProject1", art, alex, current));
//        projectService.add(new Project(2, "artProject2", art, alex, current));
//        projectService.add(new Project(3, "itProject1", it, alex, current));
//        projectService.add(new Project(4, "itProject1", it, alex, current));
//        projectService.add(new Project(5, "spaceProject1", space, alex, current));
//        projectService.add(new Project(6, "spaceProject1", space, alex, current));
//        projectService.add(new Project(7, "gamesProject1", games, alex, current));
//        projectService.add(new Project(8, "gamesProject1", games, alex, current));
        ProjectDao projectDao = new ProjectDaoImpl();
        ProjectService projectService = new ProjectServiceImpl(projectDao);
        List<Project> projectList = projectService.getProjectsByCategoryId(5);
        for (Project project : projectList) {
            System.out.println(project.getProjectName());
        }
    }
<<<<<<< HEAD
  }
=======
>>>>>>> 57ee57ac1765ce844777ffd09e750dd70873c054
}
