package ua.goit.dao;

import ua.goit.factory.DBConnectionManager;
import ua.goit.model.User;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
  private final DBConnectionManager connectionManager = DBConnectionManager.getInstance();
  @Override
  public void add(User entity) {
    PreparedStatement statement = null;
    String sql = "INSERT INTO Users (id, name, login, password, token, timeStamp) VALUES (?,?,?,?,?,?)";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, entity.getId());
      statement.setString(2, entity.getName());
      statement.setString(3, entity.getLogin());
      statement.setString(4, entity.getPassword());
      statement.setInt(5, entity.getToken());
      statement.setTimestamp(6, getCurrentTimeStamp());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }
  }
  @Override
  public User getById(Integer id) {
    Statement statement = null;
    User user = null;
    String sql = "SELECT id, name, login, password, token, timestamp FROM Users WHERE id = " + id;
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        user = getUser(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return user;
  }
  @Override
  public List<User> getAll() {
    Statement statement = null;
    User user = null;
    List<User> listWithUser = new ArrayList<>();
    String sql = "SELECT id, name, login, password, token, timestamp FROM Users";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        user = getUser(rs);
        listWithUser.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionManager.freeConnection(connection);
    }

    return listWithUser;
  }
  @Override
  public void update(User entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE Users SET name = ?, login = ?, password = ? WHERE id = ?";
    Connection connection = null;
    try {
      connection = connectionManager.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getName());
      statement.setString(2, entity.getLogin());
      statement.setString(3, entity.getPassword());
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
    String sql = "DELETE FROM Users WHERE id = ?";
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

  private User getUser(ResultSet rs) throws SQLException {
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    String login = rs.getString("login");
    String password = rs.getString("password");
    Integer token = rs.getInt("token");
    Timestamp timestamp = rs.getTimestamp("timeStamp");
    return new User(id, name, login, password, token, timestamp);
  }

  private Timestamp getCurrentTimeStamp() {
    java.util.Date today = new java.util.Date();
    return new Timestamp(today.getTime());
  }
}
