package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.IngredientCommand;
import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.commands.UnitOfMeasureCommand;
import dxy.springframework.adspringrecipeapp.services.IngredientService;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import dxy.springframework.adspringrecipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author AD
 * @date 2020/10/22
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable Long id, Model model) {
        RecipeCommand recipeCommandById = recipeService.findRecipeCommandById(Long.valueOf(id));
        log.debug("getting the recipe from the recipe id...");
        model.addAttribute("recipe", recipeCommandById);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        IngredientCommand ingredientCommand = ingredientService.findIngredientByIdAndRecipeId(ingredientId, recipeId);
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findIngredientByIdAndRecipeId(Long.valueOf(ingredientId), Long.valueOf(recipeId)));
        model.addAttribute("uomList", unitOfMeasureService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand saveIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("saved recipe id: " + saveIngredientCommand.getRecipeId());
        log.debug("saved ingredient id: " + saveIngredientCommand.getId());

        return "redirect:/recipe/" + saveIngredientCommand.getRecipeId() + "/ingredient/" + saveIngredientCommand.getId() + "/show";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String addNewIngredient(@PathVariable String recipeId, Model model) {
        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.findAll());

        return "recipe/ingredient/ingredientform";
    }
}
