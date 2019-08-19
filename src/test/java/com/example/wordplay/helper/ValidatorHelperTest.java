package com.example.wordplay.helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorHelperTest {

    ValidatorHelper validatorHelper;

    @Before
    public void setUp() throws Exception {
        validatorHelper = new ValidatorHelper();
    }

    @Test
    public void testInvalidDistribution() {
        assertFalse(validatorHelper.isValidDistributionRequest("AAAAZZZ"));
        assertTrue(validatorHelper.isValidDistributionRequest("AAAAZBC"));
    }

}