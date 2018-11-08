package com.dmitrybondarev.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Log4j
public class HomeController {

    @GetMapping
    public String getHomePage(HttpServletRequest request) {
        log.info("Home GET request");
        return "home";
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ModelAndView handlerNotFound() {
//        return new ModelAndView("redirect:/error");
//    }
}
