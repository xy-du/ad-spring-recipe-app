package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.exceptions.NotFoundException;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author AD
 * @date 2020/10/21
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private static final String VIEW_RECIPE_FORM = "recipe/recipeform";

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
        return VIEW_RECIPE_FORM;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return VIEW_RECIPE_FORM;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });
            return VIEW_RECIPE_FORM;
        }
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + recipeCommand.getId();
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        log.debug("the deleted id is " + id);
        return "redirect:/";
    }


    //the @ExceptionHandler here decide that this method will deal with the NotFoundException type of exception,
    //and the @ResponseStatus here decide what http status code this method will return.
    //Do note that, without this @ResponseStatus here to specify the status code. the client side won't get
    //the Not_FOUND code  even thought it's written on the exception class itself already, since the code
    // got overwritten here. so if you want the not_found code get returned, you have to specify the code
    // here on this method.
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView errorView404(Exception e) {
        //add Exception parameter to tell spring pass it over in this method
        log.error("Handling Not Found Exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

}
