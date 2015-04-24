package ua.goit.model;

import java.sql.Timestamp;
import java.util.List;

public class Category {
  private Integer id;
  private String categoryName;
  private List<Project> listOfProject;
  private Timestamp timestamp;

  public Category(Integer id, String categoryName, Timestamp timestamp) {
    this.id = id;
    this.categoryName = categoryName;
    this.timestamp = timestamp;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<Project> getListOfProject() {
    return listOfProject;
  }

  public void setListOfProject(List<Project> listOfProject) {
    this.listOfProject = listOfProject;
  }

}
