package ua.goit.dao;

import ua.goit.model.Category;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CategoryDaoImpl implements CategoryDao {
  private final Connection connection = Factory.getConnectionFactory().getConnection();

  public void add(Category entity) {
    PreparedStatement statement = null;
    String sql = "insert into Category(name, timeStamp) values(?,?)";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getCategoryName());
      statement.setTimestamp(2, getCurrentTimeStamp());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public Category getById(Integer id) {
    Statement statement = null;
    Category category = null;
    String sql = "SELECT id, name , timeStamp FROM Category WHERE id = " + id;
    try {
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        String name = rs.getString("name");
        Timestamp timestamp= rs.getTimestamp("timeStamp");
        category = new Category(id, name, timestamp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return category;
  }
  public List<Category> getAll() {
    Statement statement = null;
    Category category = null;
    List<Category> listWithCategory = new ArrayList<Category>();
    String sql = "SELECT id, name , timeStamp FROM Category";
    try {
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Timestamp timestamp= rs.getTimestamp("timeStamp");
        category = new Category(id, name, timestamp);
        listWithCategory.add(category);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return listWithCategory;
  }
  public void update(Category entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE Category SET name = ?, WHERE id = ? ";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getCategoryName());
      statement.setInt(2, entity.getId());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public void remove(Integer id) {
    PreparedStatement statement = null;
    String sql = "DELETE FROM Category WHERE id = ? ";
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
}
