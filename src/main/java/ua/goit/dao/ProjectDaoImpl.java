package ua.goit.dao;

import ua.goit.factory.DBConnectionManager;
import ua.goit.model.Category;
import ua.goit.model.Project;
import ua.goit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
  private final DBConnectionManager connectionManager = DBConnectionManager.getInstance();
  private final DaoFactory daoFactory = Factory.getDaoFactory();

  @Override
  public void add(Project entity) {
    PreparedStatement statement = null;
    String sql = "insert into Project (name, timeStamp, Category_id, Users_id) values(?,?,?,?)";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getProjectName());
      statement.setTimestamp(2, getCurrentTimeStamp());
      statement.setInt(3, entity.getCategory().getId());
      statement.setInt(4, entity.getUser().getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }
  }

  @Override
  public Project getById(Integer id) {
    Statement statement = null;
    Project project = null;
    String sql = "SELECT id, name, Category_id, Users_id, timeStamp, FROM Project WHERE id = " + id;
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      CategoryDao categoryDao = daoFactory.getCategoryDao();
      UserDao userDao = daoFactory.getUserDao();
      while (rs.next()) {
        project = getProject(rs, categoryDao, userDao);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return project;
  }

  @Override
  public List<Project> getAll() {
    Statement statement = null;
    Project project = null;
    List<Project> listWithProject = new ArrayList<>();
    String sql = "SELECT id, name, Category_id, Users_id, timeStamp, FROM Project";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      CategoryDao categoryDao = daoFactory.getCategoryDao();
      UserDao userDao = daoFactory.getUserDao();
      while (rs.next()) {
        project = getProject(rs, categoryDao, userDao);
        listWithProject.add(project);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return listWithProject;
  }

  @Override
  public void update(Project entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE Project SET name = ?, Category_id =?, Users_id = ? WHERE id = ?";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getProjectName());
      statement.setInt(2, entity.getCategory().getId());
      statement.setInt(3, entity.getUser().getId());
      statement.setInt(4, entity.getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }
  }

  @Override
  public void remove(Integer id) {
    PreparedStatement statement = null;
    String sql = "DELETE FROM Project WHERE id = ?";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }
  }

  @Override
  public List<Project> getProjectsByCategoryId(Integer categoryId) {
    Statement statement = null;
    Project project = null;
    List<Project> listWithProject = new ArrayList<>();
    Connection connection = null;
    String sql = "SELECT id, name, timeStamp, Category_id, Users_id FROM Project WHERE Category_id = " + categoryId;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery(sql);
      CategoryDao categoryDao = daoFactory.getCategoryDao();
      UserDao userDao = daoFactory.getUserDao();
      while (rs.next()) {
        project = getProject(rs, categoryDao, userDao);
        listWithProject.add(project);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return listWithProject;
  }

  private Timestamp getCurrentTimeStamp() {
    Date today = new java.util.Date();
    return new Timestamp(today.getTime());
  }

  private Project getProject(ResultSet rs, CategoryDao categoryDao, UserDao userDao) throws SQLException {
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    Integer categoryId = rs.getInt("Category_id");
    Integer userId = rs.getInt("Users_id");
    Category category = categoryDao.getById(categoryId);
    User user = userDao.getById(userId);
    Timestamp timestamp = rs.getTimestamp("timeStamp");
    return new Project(id, name, category, user, timestamp);
  }
}

