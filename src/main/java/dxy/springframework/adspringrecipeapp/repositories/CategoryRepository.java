package dxy.springframework.adspringrecipeapp.repositories;

import dxy.springframework.adspringrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author AD
 * @date 2020/10/17
 */
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
