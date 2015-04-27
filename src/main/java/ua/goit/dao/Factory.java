package ua.goit.dao;

public class Factory {
  private Factory() {
  }
  public static DaoFactory getDaoFactory() {
    return new DaoFactory();
  }
}
