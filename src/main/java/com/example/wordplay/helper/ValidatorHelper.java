package com.example.wordplay.helper;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ValidatorHelper {

    static Map<String, Integer> letterDistribution;

    @Value("${seven.letters.or.more.validation}")
    boolean validateByLetterCount;

    public void setValidateByLetterCount(boolean validateByLetterCount) {
        this.validateByLetterCount = validateByLetterCount;
    }

    static {

        letterDistribution = new HashMap<>();

        Properties properties = new Properties();

        try {
            properties.load(ValidatorHelper.class.getResourceAsStream("/static/distribution.properties"));
        } catch (IOException e) {
            //TODO: How to handle exceptions in a static block
        }

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            letterDistribution.put(key, Integer.valueOf(value));
        }

    }

    public boolean isValidLetterCount(String letters) {
        if(validateByLetterCount) {
            if(letters.length() < 7) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidDistributionRequest(String letters) {
        letters = letters.toUpperCase();

        char[] charray = letters.toCharArray();
        Map<String, Integer> letterDistributionInRequest = new HashMap<>();
        for(int i=0;i<letters.length();i++) {
            String key = String.valueOf(charray[i]);
            if(letterDistributionInRequest.containsKey(key)) {
                int currentCount = letterDistributionInRequest.get(key);

                if(letterDistribution.get(key) < currentCount) {
                    return false;
                }

                letterDistributionInRequest.put(key, currentCount+1);
            } else {
                letterDistributionInRequest.put(key, 1);
            }
        }
        return true;
    }

}
