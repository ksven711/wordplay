package com.example.wordplay.utilities;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class CommonUtilsTest {


    @Test
    public void testMapCreation() {
        Map<Integer, List<String>> wordMap;

        wordMap = CommonUtils.createWordMap(Collections.emptyList());
        assertThat(wordMap.size(), is(0));

        wordMap = CommonUtils.createWordMap(Collections.emptySet());
        assertThat(wordMap.size(), is(0));

        wordMap = CommonUtils.createWordMap(Arrays.asList("Bart", "Lisa", "Marge", "Homer", "Maggie"));
        assertThat(wordMap.size(), is(not(0)));

        assertThat(wordMap.containsKey(3), is(false));
        assertThat(wordMap.get(4).size(), is(2));
        assertThat(wordMap.get(5).size(), is(2));
        assertThat(wordMap.get(6).size(), is(1));
        assertThat(wordMap.get(6).get(0), is("Maggie"));

    }

}