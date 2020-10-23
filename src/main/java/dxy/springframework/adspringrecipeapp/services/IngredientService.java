package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.commands.IngredientCommand;

/**
 * @author AD
 * @date 2020/10/22
 */
public interface IngredientService {
    IngredientCommand findIngredientByIdAndRecipeId(Long ingredientId, Long recipeId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredientByIdAndRecipeId(Long ingredientId, Long recipeId);
}
