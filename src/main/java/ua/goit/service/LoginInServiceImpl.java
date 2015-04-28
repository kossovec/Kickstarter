package ua.goit.service;

import ua.goit.model.User;

public class LoginInServiceImpl implements LoginInService {
  private final UserService userService;
  private User user;

  public LoginInServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public User getUser(String login) {
    user = userService.getByLogin(login);
    return user;
  }

  @Override
  public boolean checkPassword(User user, String password) {
    if (user != null) {
      String pass = user.getPassword();
      return pass.equals(password);
    } else {
      throw new RuntimeException("User not found!!!");
    }
  }

  @Override
  public Integer generateToken(User user) {
    Integer token;
    token = 31 * user.getId() + user.getLogin().hashCode();
    user.setToken(token);
    return token;
  }

  @Override
  public void updateToken(User user) {
    if (user != null) {
      userService.update(user);
    } else {
      throw new RuntimeException("User not found!!!");
    }
  }
}
