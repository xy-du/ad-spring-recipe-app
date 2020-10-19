package dxy.springframework.adspringrecipeapp.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author AD
 * @date 2020/10/19
 */
public class CategoryTest {

    Category category = new Category();

    @Before
    public void setUp() {
        category = new Category();
    }


    @Test
    public void getId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}