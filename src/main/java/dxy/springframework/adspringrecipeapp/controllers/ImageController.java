package dxy.springframework.adspringrecipeapp.controllers;

import dxy.springframework.adspringrecipeapp.commands.RecipeCommand;
import dxy.springframework.adspringrecipeapp.services.ImageService;
import dxy.springframework.adspringrecipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author AD
 * @date 2020/10/23
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String showImageUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(recipeId), file);
        return "redirect:/recipe/show/" + recipeId;
    }

    @GetMapping("/recipe/{recipeId}/recipeimage")
    public void getImageFromDB(@PathVariable String recipeId, HttpServletResponse httpServletResponse) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));

        if (recipeCommand.getImage() != null) {
            byte[] resImageBytes = new byte[recipeCommand.getImage().length];

            int i = 0;
            for (Byte b : recipeCommand.getImage()) {
                resImageBytes[i++] = b;
            }

            InputStream inputStream = new ByteArrayInputStream(resImageBytes);
            httpServletResponse.setContentType("image/jpeg");
            IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
        }
    }
}
