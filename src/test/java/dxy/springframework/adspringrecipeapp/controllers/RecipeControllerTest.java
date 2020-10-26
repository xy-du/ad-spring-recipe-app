package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.exceptions.NotFoundException;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author AD
 * @date 2020/10/23
 */
public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    RecipeController recipeController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        Recipe returnedRecipe = recipeService.findById(anyLong());

        assertNotNull(returnedRecipe);
        assertEquals(returnedRecipe.getId(), Long.valueOf(1));
    }

    @Test
    public void newRecipe() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", Matchers.equalTo(Long.valueOf(1)))));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .param("description", "csdcscd")
                .param("directions", "csdcsdcsdcs"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/1"));
    }

    @Test
    public void saveOrUpdateWithValidation() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe").param("id", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void deleteRecipeById() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());

    }

    @Test
    public void errorPageTestNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void errorPageTestNumberFormat() throws Exception {
        mockMvc.perform(get("/recipe/show/sds"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}