package com.example.wordplay.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordplayControllerTest {

    WordplayController wordplayController;

    @Before
    public void setUp() throws Exception {
        wordplayController = new WordplayController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void index_test() {
        assertThat(wordplayController.index(), is("Hello Wordplay world!"));

    }


}