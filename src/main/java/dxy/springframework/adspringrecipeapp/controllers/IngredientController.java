package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.IngredientCommand;
import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.services.IngredientService;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author AD
 * @date 2020/10/22
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable Long id,Model model){
        RecipeCommand recipeCommandById = recipeService.findRecipeCommandById(Long.valueOf(id));
        log.debug("getting the recipe from the recipe id...");
        model.addAttribute("recipe",recipeCommandById);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId,@PathVariable Long ingredientId,Model model){
        IngredientCommand ingredientCommand = ingredientService.findIngredientByIdAndRecipeId(ingredientId, recipeId);
        model.addAttribute("ingredient",ingredientCommand);
        return "recipe/ingredient/show";
    }


}
