package com.example.wordplay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordplayController {

    @RequestMapping("/")
    public String index() {
        return "Hello Wordplay world!";
    }
}
