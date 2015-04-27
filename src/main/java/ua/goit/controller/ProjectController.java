package ua.goit.controller;

import ua.goit.dao.ProjectDao;
import ua.goit.dao.ProjectDaoImpl;
import ua.goit.model.Project;
import ua.goit.service.ProjectService;
import ua.goit.service.ProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ProjectController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    Integer categoryId = Integer.parseInt(req.getParameter("category"));

    ProjectDao projectDao = new ProjectDaoImpl();
    ProjectService projectService = new ProjectServiceImpl(projectDao);
    List<Project> projects = projectService.getProjectsByCategoryId(Integer.parseInt(categoryId.toString()));

    String url = "/projects.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    req.setAttribute("projects", projects);
    dispatcher.forward(req, resp);
  }
}
