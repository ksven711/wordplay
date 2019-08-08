package com.example.wordplay.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

public class WordServiceTest {

    WordService wordService;
    StopWatch stopWatch;

    @Before
    public void setUp() throws Exception {
        wordService = new WordService();
        stopWatch = new StopWatch();
    }

    @Test
    public void testGetAllWords_correctKey() {
//        stopWatch.start("Load all words");
        List<String> words = wordService.getAllWords("OSPD");
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());

        assertThat(words, is(not(empty())));
        assertThat(words.size(), is(178691));
    }

    @Test
    public void testGetAllWords_untrimmedCorrectKey() {
        List<String> words = wordService.getAllWords(" OSPD   ");
        assertThat(words, is(not(empty())));
        assertThat(words.size(), is(178691));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllWords_incorrectKey() {
        wordService.getAllWords("OSW");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllWords_emptyKey() {
        wordService.getAllWords("");
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllWords_nullKey() {
        wordService.getAllWords(null);
    }
}