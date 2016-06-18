package com.quankm.healthdiary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Infernocorez on 6/16/2016.
 */

public class RegexValidator {
    //reference http://stackoverflow.com/questions/8204680/java-regex-email
    public static final String PATTERN_EMAL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    //reference: http://stackoverflow.com/questions/5963228/regex-for-names-with-special-characters-unicode
    public static final String PATTERN_NAME = "^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s?)+$";

    public static boolean validate(String content,String pattern){
        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(content);
        return matcher.matches();
    }
}
