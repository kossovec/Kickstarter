package ua.goit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.goit.dao.UserDao;
import ua.goit.model.User;
import java.sql.Timestamp;
import java.util.Date;

public class LoginInServiceTest {
  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private UserDao userDao;

  @Test
  public void userWithRightPassword_GetUserFromDBAndCheck_LoginOk() {
    String login = "login";
    String password = "password";
    User user = new User(1, "user", login, password, 0, new Timestamp(new Date().getTime()));
    Mockito.when(userDao.getByLogin(login)).thenReturn(user);
    UserService userService = new UserServiceImpl(userDao);
    LoginInService loginInService = new LoginInServiceImpl(userService);
    User userFromLoginService = loginInService.getUser(login);
    boolean isRightPass = loginInService.checkPassword(userFromLoginService, password);
    Assert.assertTrue(isRightPass);
    Mockito.verify(userDao).getByLogin(login);
  }
}
