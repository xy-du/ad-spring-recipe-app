package dxy.springframework.adspringrecipeapp.repositories;

import dxy.springframework.adspringrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author AD
 * @date 2020/10/19
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
//    @DirtiesContext
    public void findByDescription() {
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", teaspoon.get().getDescription());
    }

    /*
    if run all two test method once, you will find that the first method above will spend a lot more time to complete,
    and this one below is much more faster. The reason behind this is that the spring context only have to start once,
    and when it's used after the startup, things will run much faster.

    but if I add @DirtiesContext on the method above, the spring context will have to reset after that method is completed,
    which will make this one below run slower( have to wait for the spring context to be ready).
     */
    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", cup.get().getDescription());
    }
}