package dxy.springframework.adspringrecipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author AD
 * @date 2020/10/26
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView errorView400(Exception e){
        log.error("the Number Format Passed in is not right");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("400error");
        return modelAndView;
    }
}
