package com.jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {

        return "this page is not allowed to unauthenticated users";
    }

    @RequestMapping("/getusers")
    public String getUser() {
        return "{\"name\":\"Lakhan\"}";
    }

}
