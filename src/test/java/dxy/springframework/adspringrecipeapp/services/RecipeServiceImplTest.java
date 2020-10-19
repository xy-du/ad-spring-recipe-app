package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author AD
 * @date 2020/10/19
 */
public class RecipeServiceImplTest {


    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(recipe);


        //verify that when call getRecipes() from Service Layer, Repository Layer will do the work underneath
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(recipeService.getRecipes().size(),1);

        //verify that the findAll for Repository are only called once
        verify(recipeRepository,times(1)).findAll();


    }
}