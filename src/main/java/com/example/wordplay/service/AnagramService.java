package com.example.wordplay.service;

import com.example.wordplay.exception.ScrabbleException;
import com.example.wordplay.helper.ValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnagramService {

    ValidatorHelper validatorHelper;

    @Autowired
    public void setValidatorHelper(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    public Set<String> getAllAnagrams(String letters) {

        if (letters == null) {
            throw new NullPointerException("Argument to method is NULL");
        }
        String trimmedLetterString = letters.trim();
        if (trimmedLetterString.length() < 2) {
            throw new IllegalArgumentException("Argument to the method should be greater than 1");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z?]*$");
        Matcher matcher = pattern.matcher(letters);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Arguments should be a-z A-Z");
        }


        if (!validatorHelper.isValidLetterCount(letters)) {
            //TODO: This will be incorrect if we turn the 7 letters or more validation flag to
            // false in application.properties
            throw new ScrabbleException(letters + " contains less than 7 characters");
        }

        if (!validatorHelper.isValidDistributionRequest(letters)) {
            throw new ScrabbleException(letters + " is an INVALID request. ");
        }

        System.out.println("LETTERS: " + letters);
        int numberOfWildcards = StringUtils.countOccurrencesOf(letters, "?");
        System.out.println("Number of wildcards: " + numberOfWildcards);

        Set<String> anagrams = new HashSet<>();

        permutationMain(anagrams, letters);
        doWildcardSubstitution(anagrams, numberOfWildcards);

        System.out.println("Number of anagrams: " + anagrams.size());
        return anagrams;
    }

    private void doWildcardSubstitution(Set<String> anagrams, int numberOfWildcards) {

        if (numberOfWildcards != 0) {

            Set<String> strSet = new HashSet<>();

            for (int i = 0; i < numberOfWildcards; i++) {
                for (String word : anagrams) {
                    for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
                        String wordToAdd = word.replaceFirst("\\?", Character.toString(alphabet));
                        strSet.add(wordToAdd);
                    }

                }
                anagrams.clear();
                anagrams.addAll(strSet);
                strSet.clear();
            }
        }
    }

    public void permutationMain(Set<String> words, String str) {
        permutation(words, "", str);
    }

    private void permutation(Set<String> words, String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            words.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(words, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

/*
    public static void main(String... args) {
        Set<String> baseSet = new HashSet<>();
        baseSet.add("AB?C?");

        Set<String> strSet = new HashSet<>();

        int numberOfWildcards=2;
        for (int i = 0; i < numberOfWildcards; i++) {
            for (String word : baseSet) {
                for(char alphabet = 'A'; alphabet <='Z'; alphabet++ ) {
                    String wordToAdd = word.replaceFirst("\\?", Character.toString(alphabet));
                    strSet.add(wordToAdd);
                }

            }
            baseSet.clear();
            baseSet.addAll(strSet);
            strSet.clear();
        }

        System.out.println("size: "+baseSet.size());
        for (String s : baseSet) {
            System.out.println(s);
        }


    }
*/

}
