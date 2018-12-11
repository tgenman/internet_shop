package com.dmitrybondarev.shop.web.controller;

import com.dmitrybondarev.shop.service.api.MessageEmitter;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MessageEmitter messageEmitter;

    @Loggable
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @Loggable
    @GetMapping("/admin")
    public String showAdminPanel() {
        return "admin/adminPanel";
    }

    @Loggable
    @GetMapping("/sendmessage")
    public String sendMessage() {
        messageEmitter.produceMessage("Top");
        return "mock";
    }

}
