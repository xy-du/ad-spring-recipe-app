package dxy.springframework.adspringrecipeapp.services;

import dxy.springframework.adspringrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @author AD
 * @date 2020/10/22
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAll();
}
