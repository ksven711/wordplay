package com.example.wordplay.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class WordService {

    public static final Map<String, String> dictionaryMap;
    private static final String KEY_NOT_FOUND_ERR_MESSAGE_PREFIX = "Dictionary does not exist ";
    private static final String KEY_NULL_ERR_MESSAGE_PREFIX = "Dictionary key is null";

    static {
        dictionaryMap = new HashMap<>();
        dictionaryMap.put("OSPD", "/static/dictionary.txt");
    }

    public List<String> getAllWords(String dictionary) {
        List<String> words = Collections.emptyList();

        if(dictionary == null) {
            throw new NullPointerException(KEY_NULL_ERR_MESSAGE_PREFIX);
        }

        String trimmedDictionaryValue = dictionary.trim();

        if(!dictionaryMap.containsKey(trimmedDictionaryValue)) {
            throw new IllegalArgumentException(KEY_NOT_FOUND_ERR_MESSAGE_PREFIX + trimmedDictionaryValue);
        }

        String file = dictionaryMap.get(trimmedDictionaryValue);
        try {
            URL resource = getClass().getResource(file);
            URI uri = resource.toURI();
            Path path = Paths.get(uri);
            words = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            //TODO: Handle exception correctly
        } catch (URISyntaxException e) {
            //TODO: Handle exception correctly
        }
        return words;
    }
}
