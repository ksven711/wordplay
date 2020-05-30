package com.example.wordplay.service;

import com.example.wordplay.exception.ScrabbleException;
import com.example.wordplay.helper.ValidatorHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnagramServiceTest {

    @Mock
    ValidatorHelper validatorHelper;

    @InjectMocks
    AnagramService anagramService;

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

    @Test(expected = ScrabbleException.class)
    public void getAllAnagrams_invalidDistributionOfLetters() throws Exception {
        when(validatorHelper.isValidLetterCount(anyString())).thenReturn(true);
        when(validatorHelper.isValidDistributionRequest(anyString())).thenReturn(false);

        anagramService.getAllAnagrams("AAAAZZZ");
    }

    @Test(expected = ScrabbleException.class)
    public void getAllAnagrams_validLetterCount() throws Exception {

        when(validatorHelper.isValidLetterCount(anyString())).thenReturn(false);

        anagramService.getAllAnagrams("AA");
    }

    @Test
    public void getAllAnagrams() {

        when(validatorHelper.isValidLetterCount(anyString())).thenReturn(true);
        when(validatorHelper.isValidDistributionRequest(anyString())).thenReturn(true);

        assertThat(anagramService.getAllAnagrams("AB").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("ABC").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("AAAA").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("aaa").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("abCD").size(), is(greaterThan(0)));
        assertThat(anagramService.getAllAnagrams("aBcDeFG").size(), is(greaterThan(0)));
    }

    @Test
    public void getAllAnagrams_validAnagrams() {
        when(validatorHelper.isValidLetterCount(anyString())).thenReturn(true);
        when(validatorHelper.isValidDistributionRequest(anyString())).thenReturn(true);

        assertThat(anagramService.getAllAnagrams("AA").size(), is(1));
        assertThat(anagramService.getAllAnagrams("ABC").size(), is(6));
        assertThat(anagramService.getAllAnagrams("abcd").size(), is(24));
    }

    @Test
    public void getAllAnagrams_singleWildCard() {

        when(validatorHelper.isValidLetterCount(anyString())).thenReturn(true);
        when(validatorHelper.isValidDistributionRequest(anyString())).thenReturn(true);

        assertThat(anagramService.getAllAnagrams("A?").size(), is(51));
        assertThat(anagramService.getAllAnagrams("??").size(), is(676));
        assertThat(anagramService.getAllAnagrams("STAMP??").size(), is(1392720));
    }

}