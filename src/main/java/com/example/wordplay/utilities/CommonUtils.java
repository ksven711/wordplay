package com.example.wordplay.utilities;

import java.util.*;

public class CommonUtils {

    public static Map<Integer, List<String>> createWordMap(Collection<String> wordsFromSource) {
        Map<Integer, List<String>> wordMap = new HashMap<>();

        for (String word : wordsFromSource) {
            int length = word.length();

            List<String> words;
            if (wordMap.containsKey(length)) {
                words = wordMap.get(length);
                words.add(word);
            } else {
                words = new ArrayList<>();
                words.add(word);
            }
            wordMap.put(length, words);
        }

        return wordMap;
    }
}
