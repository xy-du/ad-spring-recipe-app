package dxy.springframework.adspringrecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AD
 * @date 2020/10/13
 */
@Controller
public class indexController {
    @RequestMapping({"","/","/index","index.html"})
    public String getIndexPage(){
        return "index";
    }
}
