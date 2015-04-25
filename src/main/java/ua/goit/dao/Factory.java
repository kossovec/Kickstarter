package ua.goit.dao;

public class Factory {
  private Factory() {
  }

  public static ConnectionFactory getConnectionFactory() {
    return new ConnectionFactory();
  }

  public static DaoFactory getDaoFactory() {
    return new DaoFactory();
  }
}
