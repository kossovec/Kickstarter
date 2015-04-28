package ua.goit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.goit.dao.CategoryDao;
import ua.goit.dao.Factory;
import ua.goit.model.Category;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DaoTest {
  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private CategoryDao categoryDao;

  @Test
  public void getDataFromCategoryById() {
    Category category = new Category(1, "Music", new Timestamp(new Date().getTime()));
    Mockito.when(categoryDao.getById(1)).thenReturn(category);
    CategoryService categoryService = new CategoryServiceImpl(categoryDao);
    Assert.assertEquals(category, categoryService.getById(1));
  }

  @Test
  public void getDataFromCategoryGetAll() {
    final Category firstCategory = new Category(1, "Music", new Timestamp(new Date().getTime()));
    final Category secondCategory = new Category(2, "Art", new Timestamp(new Date().getTime()));
    List<Category> categoryList = new ArrayList(){{
      add(firstCategory);
      add(secondCategory);
    }};
    Mockito.when(categoryDao.getAll()).thenReturn(categoryList);
    CategoryService categoryService = new CategoryServiceImpl(categoryDao);
    Assert.assertEquals(categoryList, categoryService.getAll());
  }


}
