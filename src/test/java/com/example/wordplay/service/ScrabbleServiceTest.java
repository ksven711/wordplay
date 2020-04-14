package com.example.wordplay.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ScrabbleServiceTest {

    ScrabbleService scrabbleService;

    @Before
    public void setUp() throws Exception {
        scrabbleService = new ScrabbleService();

        scrabbleService.setAnagramService(new AnagramService());
        scrabbleService.setWordService(new WordService());
    }

    @Test
    public void getListOfValidWords() throws Exception {
        assertThat(scrabbleService.getListOfValidWords("LETTERS", "OSPD").size(), is(4));
    }

}