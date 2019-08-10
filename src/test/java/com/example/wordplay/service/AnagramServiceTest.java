package com.example.wordplay.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;


public class AnagramServiceTest {

    AnagramService anagramService;

    @Before
    public void setUp() throws Exception {
        anagramService = new AnagramService();
    }

    @Test(expected = NullPointerException.class)
    public void getAllAnagrams_nullPointer() throws Exception {
        anagramService.getAllAnagrams(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllAnagrams_illegalArgumentException() throws Exception {
        anagramService.getAllAnagrams("");
        anagramService.getAllAnagrams("  ");
        anagramService.getAllAnagrams("A");
        anagramService.getAllAnagrams(" A");
        anagramService.getAllAnagrams("  A  ");
        anagramService.getAllAnagrams("1");
        anagramService.getAllAnagrams("#");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllAnagrams_regexFails() throws Exception {
        anagramService.getAllAnagrams("AAAA$#");
        anagramService.getAllAnagrams("ABCD1234");
        anagramService.getAllAnagrams("12345");
        anagramService.getAllAnagrams("!@#$%^&*()");
    }

    @Test
    public void getAllAnagrams() {
        assertThat(anagramService.getAllAnagrams("AB").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("ABC").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("AAAA").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("aaa").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("abCD").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("aBcDeFG").size(), is(greaterThan(0)));
    }

    @Test
    public void getAllAnagrams_validAnagrams() {
        assertThat(anagramService.getAllAnagrams("AA").size(), is(1));
        assertThat(anagramService.getAllAnagrams("ABC").size(), is(6));
        assertThat(anagramService.getAllAnagrams("abcd").size(), is(24));
    }

}