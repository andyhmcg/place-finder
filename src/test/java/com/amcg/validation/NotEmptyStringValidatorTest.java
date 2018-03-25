package com.amcg.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class NotEmptyStringValidatorTest {

    private NotEmptyStringValidator notEmptyStringValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setup(){
        notEmptyStringValidator = new NotEmptyStringValidator();
    }

    @Test
    public void whenNonNullAndNonEmptyShouldreturnTrue() {

        boolean result = notEmptyStringValidator.isValid("ANYNAME", constraintValidatorContext);
        assertThat("Result", result, is(true));

    }

    @Test
    public void whenNonNullEmptyShouldreturnFalse() {

        boolean result = notEmptyStringValidator.isValid("", constraintValidatorContext);
        assertThat("Result", result, is(false));

    }

    @Test
    public void whenNullShouldReturnFalse(){

        boolean result = notEmptyStringValidator.isValid(null, constraintValidatorContext);
        assertThat("Result", result, is(false));
    }
}