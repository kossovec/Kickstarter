package ua.goit.model;

import java.sql.Timestamp;

public class User {
  private Integer id;

  public User(Integer id, String name, String login, String password, int token, Timestamp timestamp) {
    this.id = id;
    this.name = name;
    this.login = login;
    this.password = password;
    this.token = token;
    this.timestamp = timestamp;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getToken() {
    return token;
  }

  public void setToken(Integer token) {
    this.token = token;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  private String name;
  private String login;
  private String password;
  private Integer token;
  private Timestamp timestamp;


}
