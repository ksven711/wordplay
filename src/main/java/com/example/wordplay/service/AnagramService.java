package com.example.wordplay.service;

import com.example.wordplay.exception.ScrabbleException;
import com.example.wordplay.helper.ValidatorHelper;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramService {

    ValidatorHelper validatorHelper;

    public AnagramService() {
        this.validatorHelper = new ValidatorHelper();
    }

    public Set<String> getAllAnagrams(String letters) {

        if(letters == null) {
            throw new NullPointerException("Argument to method is NULL");
        }
        String trimmedLetterString = letters.trim();
        if(trimmedLetterString.length() < 2) {
            throw new IllegalArgumentException("Argument to the method should be greater than 1");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
        Matcher matcher = pattern.matcher(letters);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Arguments should be a-z A-Z");
        }

        if(!validatorHelper.isValidDistributionRequest(letters)) {
            throw new ScrabbleException(letters +" is an INVALID request. ");
        }

        Set<String> anagrams = new HashSet<>();
        permutation(anagrams, letters);
        return anagrams;
    }

/*    public static void main(String... args) {
        Set<String> words = new HashSet<>();
        permutation(words, "ABC");
        for (String word : words) {
            System.out.println(word);
        }
    }*/

    public void permutation(Set<String> words, String str) {
        permutation(words, "", str);
    }

    private void permutation(Set<String> words, String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            words.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(words, prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }
}