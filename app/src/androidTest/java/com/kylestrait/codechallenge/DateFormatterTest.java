package com.kylestrait.codechallenge;

import com.kylestrait.codechallenge.util.DateFormatter;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


public class DateFormatterTest {

    private DateFormatter dateFormatter;

    public DateFormatterTest() {
        dateFormatter = new DateFormatter();
    }

    // Ran out of time with this test
//    @Test
//    public void testDateValidToParse() {
//        assertThat(dateFormatter.getFormattedDate("11/3/19")).isEqualTo("");
//    }
}
