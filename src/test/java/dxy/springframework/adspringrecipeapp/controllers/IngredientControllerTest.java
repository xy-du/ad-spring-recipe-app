package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.IngredientCommand;
import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.services.IngredientService;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import dxy.springframework.adspringrecipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author AD
 * @date 2020/10/22
 */
public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService,ingredientService,unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }


    @Test
    public void listIngredients() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));


        verify(recipeService, times(1)).findRecipeCommandById(anyLong());

    }

    @Test
    public void showIngredient() throws Exception {
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findIngredientByIdAndRecipeId(anyLong(),anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void updateIngredient() throws Exception {
        when(ingredientService.findIngredientByIdAndRecipeId(anyLong(),anyLong())).thenReturn(new IngredientCommand());
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(view().name("recipe/ingredient/ingredientform"));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(1L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/ingredient"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/1/show"));
    }

}