package ua.goit.service;

import ua.goit.dao.Factory;
import ua.goit.dao.ProjectDao;
import ua.goit.model.Project;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

  private final ProjectDao projectDao;

  public ProjectServiceImpl(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  @Override
  public void add(Project entity) {
    projectDao.add(entity);
  }

  @Override
  public Project getById(Integer id) {
    return projectDao.getById(id);
  }

  @Override
  public List<Project> getAll() {
    return projectDao.getAll();
  }

  @Override
  public void update(Project entity) {
    projectDao.update(entity);
  }

  @Override
  public void remove(Integer id) {
    projectDao.remove(id);
  }

  @Override
  public List<Project> getProjectsByCategoryId(Integer categoryId) {
    return projectDao.getProjectsByCategoryId(categoryId);
  }
}
