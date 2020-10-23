package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.domain.Recipe;
import dxy.springframework.adspringrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author AD
 * @date 2020/10/23
 */

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Receive a File");
        try {
            Recipe recipe=recipeRepository.findById(recipeId).get();
            Byte[] bytes=new Byte[file.getBytes().length];

            int i=0;
            for (byte b: file.getBytes()){
                bytes[i++]=b;
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }

    }
}
