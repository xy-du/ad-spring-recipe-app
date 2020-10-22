package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.commands.IngredientCommand;
import dxy.springframework.adspringrecipeapp.converters.IngredientToIngredientCommand;
import dxy.springframework.adspringrecipeapp.domain.Ingredient;
import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @author AD
 * @date 2020/10/22
 */

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findIngredientByIdAndRecipeId(Long ingredientId, Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            //todo add error logic here - no according recipe
            log.debug("there is no such a Recipe");
        }

        Recipe recipe = recipeOptional.get();
        Set<Ingredient> ingredients = recipe.getIngredients();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        if(!ingredientCommandOptional.isPresent()){
            //todo add error logic here - no according ingredient
            log.debug("there is no such a Ingredient");
        }
        return ingredientCommandOptional.get();
    }
}
