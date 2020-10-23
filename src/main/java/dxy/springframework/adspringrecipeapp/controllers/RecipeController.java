package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author AD
 * @date 2020/10/21
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model) {
        Long rid = new Long(id);
        Recipe recipe = recipeService.findById(rid);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + recipeCommand.getId();
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        log.debug("the deleted id is " + id);
        return "redirect:/";
    }


}
