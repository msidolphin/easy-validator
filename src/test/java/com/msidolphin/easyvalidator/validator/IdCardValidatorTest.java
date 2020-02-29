package com.msidolphin.easyvalidator.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class IdCardValidatorTest extends BaseValidatorTest {

    @Test
    public void test () {
        new IdCardValidator().validate("110101199003070716", null, null);
    }

    @Test
    public void testFailed () {
        tryCatch(() -> {
            // length
            new IdCardValidator().validate("11010119900307071", null, null);
        });
        tryCatch(() -> {new IdCardValidator().validate("110101199003070", null, null);});
        // area
        tryCatch(() -> {
            new IdCardValidator().validate("910101199003070711", null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate("110101199003070711", null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate(null, null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate("", null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate(1, null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate("12321312aaaa", null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate(new HashMap<>(), null, null);
        });
        tryCatch(() -> {
            new IdCardValidator().validate(new ArrayList<>(), null, null);
        });
    }

}
