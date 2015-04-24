package ua.goit.dao;

public class DaoFactory {

  public CategoryDao getCategoryDao() {
    return new CategoryDaoImpl();
  }

  public ProjectDao getProjectDao() {
    return new ProjectDaoImpl();
  }

  public UserDao getUserDao() {
    return new UserDaoImpl();
  }
}
