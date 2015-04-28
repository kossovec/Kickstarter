package ua.goit.controller;

import ua.goit.dao.CategoryDao;
import ua.goit.dao.Factory;
import ua.goit.model.Category;
import ua.goit.service.CategoryService;
import ua.goit.service.CategoryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    CategoryDao categoryDao = Factory.getDaoFactory().getCategoryDao();
    CategoryService categoryService = new CategoryServiceImpl(categoryDao);
    List<Category> categories = categoryService.getAll();
    String url = "/categories.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    req.setAttribute("categories", categories);
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    String categoryName = req.getParameter("category_name");
    CategoryDao categoryDao = Factory.getDaoFactory().getCategoryDao();
    CategoryService categoryService = new CategoryServiceImpl(categoryDao);
    categoryService.add(new Category(null, categoryName, null));
    List<Category> categories = categoryService.getAll();
    String url = "/categories.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    req.setAttribute("categories", categories);
    dispatcher.forward(req, resp);

  }
}
