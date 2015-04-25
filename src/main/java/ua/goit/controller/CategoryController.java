package ua.goit.controller;

import ua.goit.dao.CategoryDao;
import ua.goit.dao.CategoryDaoImpl;
import ua.goit.service.CategoryService;
import ua.goit.service.CategoryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CategoryDao categoryDao = new CategoryDaoImpl();
    CategoryService categoryService = new CategoryServiceImpl(categoryDao);
    resp.setContentType("text/html");
    String url = req.getContextPath() + "/categories.jsp";
    req.setAttribute("categories", categoryService.getAll());
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    dispatcher.forward(req, resp);
  }
}
