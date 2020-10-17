package dxy.springframework.adspringrecipeapp.repositories;

import dxy.springframework.adspringrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author AD
 * @date 2020/10/17
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
