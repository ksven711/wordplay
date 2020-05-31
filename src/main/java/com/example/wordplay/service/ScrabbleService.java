package com.example.wordplay.service;

import com.example.wordplay.exception.ScrabbleException;
import com.example.wordplay.utilities.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScrabbleService {

    WordService wordService;

    AnagramService anagramService;

    private List<String> wordsFromDictionary = new ArrayList<>();
    private Map<Integer, List<String>> possibleWordMap = new HashMap<>();

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    @Autowired
    public void setAnagramService(AnagramService anagramService) {
        this.anagramService = anagramService;
    }

    public List<String> getListOfValidWords(String letters, String dictionary) {

        List<String> result = new ArrayList<>();
        int numberOfLetters = letters.length();

        if (wordsFromDictionary.isEmpty()) {
            wordsFromDictionary = wordService.getAllWords(dictionary);
        }

        Map<Integer, List<String>> dictionaryWordMap = CommonUtils.createWordMap(wordsFromDictionary);


        Set<String> possibleWords = new HashSet<>();
        try {
            possibleWords = anagramService.getAllAnagrams(letters);
        } catch (IllegalArgumentException ie) {
            System.out.println("Illegal argument");
        } catch (ScrabbleException se) {
            System.out.println("Contains less than seven characters");
        }

        possibleWordMap = CommonUtils.createWordMap(possibleWords);
        if (!possibleWords.isEmpty()) {
            result = dictionaryWordMap.get(numberOfLetters).stream()
                    .filter(possibleWordMap.get(numberOfLetters)::contains)
                    .collect(Collectors.toList());
        }

        return result;
    }

}
