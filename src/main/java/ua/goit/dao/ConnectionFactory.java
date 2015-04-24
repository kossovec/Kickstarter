package ua.goit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  public static Connection getConnection() {

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
      return connection;
    }
}
