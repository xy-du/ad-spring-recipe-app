package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.domain.Recipe;

import java.util.Set;

/**
 * @author AD
 * @date 2020/10/17
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);

    RecipeCommand findRecipeCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
