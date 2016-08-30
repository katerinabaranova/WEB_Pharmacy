package com.baranova.pharmacy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check correctness of forms input.
 */
public class PatternCheck {
    /**
     * Chick if some input appropriate definite pattern
     * @param pattern
     * @param input - String form input
     * @return boolean value if input appropriate pattern
     */
    public static boolean checkPatternAppropriateness(String pattern,String input){
        Pattern p1=Pattern.compile(pattern);
        Matcher m1=p1.matcher(input);
        return m1.matches();
    }
}
