package com.example.springsessiondemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = {"","/"})
    public String helloWorld()
    {
        return "index";
    }
}
