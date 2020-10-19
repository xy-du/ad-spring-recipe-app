package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author AD
 * @date 2020/10/19
 */
public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        assertEquals("index",indexController.getIndexPage(model));

        verify(recipeService,times(1)).getRecipes();

//        verify(model,times(1)).addAttribute("recipes",recipeService.getRecipes());
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
    }
}