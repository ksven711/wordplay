package com.example.wordplay.service;

import com.example.wordplay.utilities.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScrabbleService {

    WordService wordService;
    AnagramService anagramService;

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    @Autowired
    public void setAnagramService(AnagramService anagramService) {
        this.anagramService = anagramService;
    }

    public List<String> getListOfValidWords(String letters, String dictionary) {

        int numberOfLetters = letters.length();
        List<String> wordsFromDictionary = wordService.getAllWords(dictionary);
        Map<Integer, List<String>> dictionaryWordMap = CommonUtils.createWordMap(wordsFromDictionary);

        Set<String> possibleWords = anagramService.getAllAnagrams(letters);
        Map<Integer, List<String>> possibleWordMap = CommonUtils.createWordMap(possibleWords);

        List<String> result = dictionaryWordMap.get(numberOfLetters).stream()
                .filter(possibleWordMap.get(numberOfLetters)::contains)
                .collect(Collectors.toList());

        return result;
    }

}
