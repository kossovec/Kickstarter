package ua.goit.dao;

import ua.goit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
  private final Connection connection = Factory.getConnectionFactory().getConnection();
  private final DaoFactory daoFactory = Factory.getDaoFactory();

  public void add(User entity) {
    PreparedStatement statement = null;
    String sql = "INSERT INTO users (id, name, login, password, token, timeStamp) VALUES (?,?,?,?,?,?)";
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, entity.getId());
      statement.setString(2, entity.getName());
      statement.setString(3, entity.getLogin());
      statement.setString(4, entity.getPassword());
      statement.setInt(5, entity.getToken());
      statement.setTimestamp(6, entity.getTimestamp());

      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public User getById(Integer id) {
    Statement statement = null;
    User user = null;
    String sql = "SELECT id, name, login, password, token, timestamp FROM users WHERE id = " + id;
    try {
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        user = getUser(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }

  private User getUser(ResultSet rs) throws SQLException{
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    String login = rs.getString("login");
    String password = rs.getString("password");
    Integer token = rs.getInt("token");
    Timestamp timestamp = rs.getTimestamp("timeStamp");
    return new User(id, name, login, password, token, timestamp);
  }

  public List<User> getAll() {
    Statement statement = null;
    User user = null;
    List<User> listWithUser = new ArrayList<>();
    String sql = "SELECT id, name, login, password, token, timestamp FROM users";
    try {
      statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        user = getUser(rs);
        listWithUser.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return listWithUser;
  }

  public void update(User entity) {
    PreparedStatement statement = null;
    String sql = "UPDATE users SET name = ?, login = ?, password = ? WHERE id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, entity.getName());
      statement.setString(2, entity.getLogin());
      statement.setString(3, entity.getPassword());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void remove(Integer id) {
    PreparedStatement statement = null;
    String sql = "DELETE FROM users WHERE id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
