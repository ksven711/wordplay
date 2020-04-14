package com.example.wordplay.helper;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
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

    @Test
    public void testLetterCount() {
        validatorHelper.setValidateByLetterCount(true);
        assertThat(validatorHelper.isValidLetterCount("AAA"), is(false));
        assertThat(validatorHelper.isValidLetterCount("ABCDEFG"), is(true));
        assertThat(validatorHelper.isValidLetterCount("ABCDEFGH"), is(true));
    }

}