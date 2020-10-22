package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable Long id,Model model){
        RecipeCommand recipeCommandById = recipeService.findRecipeCommandById(Long.valueOf(id));
        log.debug("getting the recipe from the recipe id...");
        model.addAttribute("recipe",recipeCommandById);
        return "recipe/ingredient/list";
    }
}
