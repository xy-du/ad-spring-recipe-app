package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.commands.UnitOfMeasureCommand;
import dxy.springframework.adspringrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dxy.springframework.adspringrecipeapp.domain.UnitOfMeasure;
import dxy.springframework.adspringrecipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author AD
 * @date 2020/10/22
 */
public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand=new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService=new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void findAll() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);


        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands=unitOfMeasureService.findAll();

        //then
        assertEquals(2, unitOfMeasureCommands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }
}