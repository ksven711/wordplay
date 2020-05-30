package com.example.wordplay.controller;

import com.example.wordplay.service.ScrabbleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordplayControllerTest {

    @Mock
    ScrabbleService scrabbleService;

    @InjectMocks
    WordplayController wordplayController;


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMethod() {

        List<String> retList = Arrays.asList("WORD", "RETURN");
        when(scrabbleService.getListOfValidWords(anyString(), anyString())).thenReturn(retList);

        assertThat(wordplayController.getAllWordsForGivenLetters("RANDOM"), is(retList));

    }


}