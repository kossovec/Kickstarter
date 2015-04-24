package ua.goit.dao;

import ua.goit.model.Category;
import ua.goit.model.Project;
import ua.goit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
  private final Connection connection = Factory.getConnectionFactory().getConnection();
  private final DaoFactory daoFactory = Factory.getDaoFactory();

  public void add(Project entity) {
    PreparedStatement statement = null;
    String sql = "insert into Project (name, timeStamp, Category_id, Users_id) values(?,?,?,?)";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getProjectName());
      statement.setTimestamp(2, getCurrentTimeStamp());
      statement.setInt(3, entity.getCategory().getId());
      statement.setInt(4, entity.getUser().getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Project getById(Integer id) {
    Statement statement = null;
    Project project = null;
    String sql = "SELECT id, name, Category_id, Users_id, timeStamp, FROM Project WHERE id = " + id;
    try {
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      CategoryDao categoryDao = daoFactory.getCategoryDao();
      UserDao userDao = daoFactory.getUserDao();
      while (rs.next()) {
        project = getProject(rs, categoryDao, userDao);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return project;
  }

  public List<Project> getAll()
  {
    Statement statement = null;
    Project project = null;
    List<Project> listWithProject = new ArrayList<>();
    String sql = "SELECT id, name, Category_id, Users_id, timeStamp, FROM Project";
    try {
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
    }

    return listWithProject;
  }

  public void update(Project entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE Project SET name = ?, Category_id =?, Users_id = ? WHERE id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getProjectName());
      statement.setInt(2, entity.getCategory().getId());
      statement.setInt(3, entity.getUser().getId());
      statement.setInt(4, entity.getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void remove(Integer id) {
    PreparedStatement statement = null;
    String sql = "DELETE FROM Project WHERE id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Timestamp getCurrentTimeStamp() {
    Date today = new java.util.Date();
    return new Timestamp(today.getTime());
  }

  private Project getProject( ResultSet rs, CategoryDao categoryDao, UserDao userDao) throws SQLException {
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    Integer categoryId = rs.getInt("Category_id");
    Integer userId = rs.getInt("User_id");
    Category category = categoryDao.getById(categoryId);
    User user = userDao.getById(userId);
    Timestamp timestamp= rs.getTimestamp("timeStamp");
    return new Project(id, name, category, user, timestamp);
  }
}
