package com.dmitrybondarev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

//    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
//    public String sayHelloAgain(ModelMap model) {
//        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
//        return "welcome";
//    }
}
