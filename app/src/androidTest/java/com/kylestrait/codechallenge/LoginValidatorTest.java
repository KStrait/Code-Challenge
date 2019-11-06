package com.kylestrait.codechallenge;

import com.kylestrait.codechallenge.util.LoginValidator;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LoginValidatorTest {

    private LoginValidator loginValidator;

    public LoginValidatorTest() {
        loginValidator = new LoginValidator();
    }

    @Test
    public void isValidEmail_returnTrue() {
        assertThat(loginValidator.validateEmail("kyle@jacapps.com")).isTrue();
    }

    @Test
    public void isValidEmail_returnFalse() {
        assertThat(loginValidator.validateEmail("test")).isFalse();
    }

    @Test
    public void isValidPassword_returnTrue() {
        assertThat(loginValidator.validatePassword("P$fi9f90wf90w")).isTrue();
    }

    @Test
    public void isValidPassword_returnFalse() {
        assertThat(loginValidator.validatePassword("password")).isFalse();
    }
}
