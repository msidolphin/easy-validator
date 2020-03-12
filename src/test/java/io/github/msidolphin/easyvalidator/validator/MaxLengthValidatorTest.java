package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.LengthConstraint;
import org.junit.Test;

import java.util.*;

public class MaxLengthValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        // string
        LengthConstraint constraint = new LengthConstraint();
        constraint.setFieldName("dialog");
        constraint.setLength(9);
        new MaxLengthValidator().validate("I am fine", constraint);
        // collection
        List<String> list = new ArrayList<>();
        list.add("a");
        new MaxLengthValidator().validate(list, new LengthConstraint(1));
        // map
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        new MaxLengthValidator().validate(map, new LengthConstraint(1));
        // array
        String[] array = new String[]{"a"};
        new MaxLengthValidator().validate(array, new LengthConstraint(1));
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {
            // string
            new MaxLengthValidator().validate("I am fine", new LengthConstraint(8));
        });
        tryCatch(() -> {
            // collection
            List<String> list = new ArrayList<>();
            list.add("a");
            new MaxLengthValidator().validate(list, new LengthConstraint(0));
        });
        tryCatch(() -> {
            // map
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            new MaxLengthValidator().validate(map, new LengthConstraint(0));
        });
        tryCatch(() -> {
            // array
            String[] array = new String[]{"a"};
            new MaxLengthValidator().validate(array, new LengthConstraint(0));
        });
    }



}
