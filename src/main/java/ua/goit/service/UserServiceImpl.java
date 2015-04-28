package ua.goit.service;

import ua.goit.dao.Factory;

import ua.goit.dao.UserDao;
import ua.goit.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void add(User entity) {
    userDao.add(entity);
  }

  @Override
  public User getById(Integer id) {
    return userDao.getById(id);
  }

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }

  @Override
  public void update(User entity) {
    userDao.update(entity);
  }

  @Override
  public void remove(Integer id) {
    userDao.remove(id);
  }

  @Override
  public User getByLogin(String login) {

    return userDao.getByLogin(login);
  }
}
