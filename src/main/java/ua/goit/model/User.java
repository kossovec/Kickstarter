package ua.goit.model;

import java.sql.Timestamp;

public class User {
  private Integer id;

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

  public String getTolken() {
    return tolken;
  }

  public void setTolken(String tolken) {
    this.tolken = tolken;
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
  private String tolken;
  private Timestamp timestamp;


}
