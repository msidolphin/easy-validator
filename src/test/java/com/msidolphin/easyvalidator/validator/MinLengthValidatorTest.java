package com.msidolphin.easyvalidator.validator;

import com.msidolphin.easyvalidator.constraint.LengthConstraint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinLengthValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        // string
        new MinLengthValidator().validate("I am fine", new LengthConstraint(9), null);
        // collection
        List<String> list = new ArrayList<>();
        list.add("a");
        new MinLengthValidator().validate(list, new LengthConstraint(1), null);
        // map
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        new MinLengthValidator().validate(map, new LengthConstraint(1), null);
        // array
        String[] array = new String[]{"a"};
        new MinLengthValidator().validate(array, new LengthConstraint(1), null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {
            // string
            new MinLengthValidator().validate("I am fine", new LengthConstraint(10), null);
        });
        tryCatch(() -> {
            // collection
            List<String> list = new ArrayList<>();
            list.add("a");
            new MinLengthValidator().validate(list, new LengthConstraint(2), null);
        });
        tryCatch(() -> {
            // map
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            new MinLengthValidator().validate(map, new LengthConstraint(2), null);
        });
        tryCatch(() -> {
            // array
            String[] array = new String[]{"a"};
            new MinLengthValidator().validate(array, new LengthConstraint(2), null);
        });
    }

}
