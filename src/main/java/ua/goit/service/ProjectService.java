package ua.goit.service;

import ua.goit.model.Project;

import java.util.List;

public interface ProjectService extends GenericService<Project> {
    List<Project> getProjectsByCategoryId(Integer categoryId);

}
