package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.LengthConstraint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinLengthValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        // string
        new MinLengthValidator().validate("I am fine", new LengthConstraint(9));
        // collection
        List<String> list = new ArrayList<>();
        list.add("a");
        new MinLengthValidator().validate(list, new LengthConstraint(1));
        // map
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        new MinLengthValidator().validate(map, new LengthConstraint(1));
        // array
        String[] array = new String[]{"a"};
        new MinLengthValidator().validate(array, new LengthConstraint(1));
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {
            // string
            new MinLengthValidator().validate("I am fine", new LengthConstraint(10));
        });
        tryCatch(() -> {
            // collection
            List<String> list = new ArrayList<>();
            list.add("a");
            new MinLengthValidator().validate(list, new LengthConstraint(2));
        });
        tryCatch(() -> {
            // map
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            new MinLengthValidator().validate(map, new LengthConstraint(2));
        });
        tryCatch(() -> {
            // array
            String[] array = new String[]{"a"};
            new MinLengthValidator().validate(array, new LengthConstraint(2));
        });
    }

}
