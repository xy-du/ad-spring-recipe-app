package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AD
 * @date 2020/10/21
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model){
        Long rid=new Long(id);
        Recipe recipe=recipeService.findById(rid);
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }

}
