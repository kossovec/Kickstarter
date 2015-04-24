package ua.goit.model;

import java.sql.Timestamp;

public class Project {
  private Integer id;
  private String projectName;
  private Category category;
  private User user;
  private Timestamp timestamp;

  public Project(Integer id, String projectName, Category category,
                 User user, Timestamp timestamp) {
    this.id = id;
    this.projectName = projectName;
    this.category = category;
    this.user = user;
    this.timestamp = timestamp;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getProjectName() {
    return projectName;
  }
}
