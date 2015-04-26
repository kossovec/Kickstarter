package ua.goit.dao;

/**
 * Created by kossovec on 24.04.15.
 */
public class Factory {
  private Factory() {
  }
  public static DaoFactory getDaoFactory() {
    return new DaoFactory();
  }
}
