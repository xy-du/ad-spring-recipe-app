package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.domain.Category;
import dxy.springframework.adspringrecipeapp.domain.UnitOfMeasure;
import dxy.springframework.adspringrecipeapp.repositories.CategoryRepository;
import dxy.springframework.adspringrecipeapp.repositories.UnitOfMeasureReposity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author AD
 * @date 2020/10/13
 */
@Controller
public class indexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureReposity unitOfMeasureReposity;

    public indexController(CategoryRepository categoryRepository, UnitOfMeasureReposity unitOfMeasureReposity) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureReposity = unitOfMeasureReposity;
    }

    @RequestMapping({"", "/", "/index", "index.html"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureReposity.findByDescription(("Teaspoon"));

        System.out.println("category id:" + categoryOptional.get().getId());
        System.out.println("unitOfMearsure id:" + unitOfMeasureOptional.get().getId());
        return "index";
    }
}
