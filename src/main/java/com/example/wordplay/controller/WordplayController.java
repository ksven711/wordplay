package com.example.wordplay.controller;

import com.example.wordplay.service.AnagramService;
import com.example.wordplay.service.ScrabbleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/words")
public class WordplayController {

    Logger logger = LoggerFactory.getLogger(WordplayController.class);


    @Autowired
    private ScrabbleService scrabbleService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllWordsForGivenLetters(@RequestParam String letters) {

        logger.info("Request input params:");
        logger.info("letters: " + letters);
        List<String> allAnagrams = scrabbleService.getListOfValidWords(letters, "OSPD");
        return allAnagrams;
    }
}
