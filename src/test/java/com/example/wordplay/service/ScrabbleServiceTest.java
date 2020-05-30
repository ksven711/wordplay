package com.example.wordplay.service;

import com.example.wordplay.helper.ValidatorHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScrabbleServiceTest {

    @Mock
    private AnagramService anagramService;

    @Mock
    private WordService wordService;

    @InjectMocks
    private ScrabbleService scrabbleService;

    @Test
    public void getListOfValidWords() throws Exception {

        List<String> retList = new ArrayList<>();
        retList.add("WORD");
        retList.add("SOMETHING");

        Set<String> retSet = new HashSet<>(retList);

        when(wordService.getAllWords("OSPD")).thenReturn(retList);
        when(anagramService.getAllAnagrams("PARK")).thenReturn(retSet);

        assertThat(scrabbleService.getListOfValidWords("PARK", "OSPD").size(), is(1));
    }

}