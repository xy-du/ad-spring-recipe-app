package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AD
 * @date 2020/10/13
 */
@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "index.html"})
    public String getIndexPage(Model model) {
        log.debug("controller---indexController---getIndexPage");
        model.addAttribute("recipes",recipeService.getRecipes());
        log.info("CircleCI Test.Just make a push");
        return "index";
    }
}
