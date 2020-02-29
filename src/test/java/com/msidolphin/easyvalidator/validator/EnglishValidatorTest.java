package com.msidolphin.easyvalidator.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class EnglishValidatorTest extends BaseValidatorTest {

    @Test
    public void test () {
        new EnglishValidator().validate("Iamfinethankyouandyou", null, null);
        new EnglishValidator().validate("abcDEfgHijklmnoPqrStuvWxYz", null, null);
    }

    @Test
    public void testFailed () {
        tryCatch(() -> {new EnglishValidator().validate(null, null, null); });
        tryCatch(() -> {new EnglishValidator().validate("", null, null); });
        tryCatch(() -> {new EnglishValidator().validate("？？？？？？", null, null); });
        tryCatch(() -> {new EnglishValidator().validate(new HashMap<>(), null, null); });
        tryCatch(() -> {new EnglishValidator().validate(new ArrayList<>(), null, null); });
    }


}
