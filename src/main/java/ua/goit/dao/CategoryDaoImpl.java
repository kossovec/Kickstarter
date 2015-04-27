package ua.goit.dao;

import ua.goit.factory.DBConnectionManager;
import ua.goit.model.Category;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CategoryDaoImpl implements CategoryDao {
  private final DBConnectionManager connectionManager = DBConnectionManager.getInstance();
  @Override
  public void add(Category entity) {
    PreparedStatement statement = null;
    String sql = "insert into Category(name, timeStamp) values(?,?)";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getCategoryName());
      statement.setTimestamp(2, getCurrentTimeStamp());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }
  }
  @Override
  public Category getById(Integer id) {
    Statement statement = null;
    Category category = null;
    String sql = "SELECT id, name , timeStamp FROM Category WHERE id = " + id;
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        category = getCategory(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return category;
  }
  @Override
  public List<Category> getAll() {
    Statement statement = null;
    Category category = null;
    List<Category> listWithCategory = new ArrayList<Category>();
    String sql = "SELECT id, name , timeStamp FROM Category";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        category = getCategory(rs);
        listWithCategory.add(category);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return listWithCategory;
  }
  @Override
  public void update(Category entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE Category SET name = ?, WHERE id = ? ";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getCategoryName());
      statement.setInt(2, entity.getId());
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
    String sql = "DELETE FROM Category WHERE id = ? ";
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

  private Category getCategory(ResultSet rs) throws SQLException {
    Category category;
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    Timestamp timestamp = rs.getTimestamp("timeStamp");
    category = new Category(id, name, timestamp);
    return category;
  }

  private Timestamp getCurrentTimeStamp() {
    Date today = new java.util.Date();
    return new Timestamp(today.getTime());
  }
}
