package dxy.springframework.adspringrecipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author AD
 * @date 2020/10/23
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
