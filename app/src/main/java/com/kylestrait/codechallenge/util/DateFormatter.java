package com.kylestrait.codechallenge.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;


// Util to format a date from each repo and make it look more readable
public class DateFormatter {

    private static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("MM/dd/yyyy h:mm:ss aaa z", Locale.US);


    public String getFormattedDate(String date) {
        String output = "";
        Date dateToFormat = null;
        if (date != null && !date.isEmpty()) {
            try {
                dateToFormat = INPUT_FORMAT.parse(date);
                output = OUTPUT_FORMAT.format(dateToFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateToFormat != null) {
                return output;
            }
        }

        return output;
    }
}
