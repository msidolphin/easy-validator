package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.DateConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class DateValidatorTest extends TestCase {

    @Test
    public void test() {
        new DateValidator().validate("2020-02-28", new DateConstraint("yyyy-MM-dd"));
        new DateValidator().validate("2020.02.28", new DateConstraint("yyyy.MM.dd"));
        new DateValidator().validate("2020年02月28日", new DateConstraint("yyyy年MM月dd日"));
        new DateValidator().validate("2020/02/28", new DateConstraint("yyyy/MM/dd"));
        new DateValidator().validate("2020年02月28日 11:14:30", new DateConstraint("yyyy年MM月dd日 HH:mm:ss"));
        new DateValidator().validate("2020年02月28日 11:14", new DateConstraint("yyyy年MM月dd日 HH:mm"));
        new DateValidator().validate("2020年02月28日 11", new DateConstraint("yyyy年MM月dd日 HH"));
    }

    @Test
    public void testFailed () {
        tryCatch(null, "yyyy-MM-dd", null);
        tryCatch("", "yyyy-MM-dd", null);
        tryCatch(1, "yyyy-MM-dd", null);
        tryCatch("11111", "yyyy-MM-dd", null);
        tryCatch("2020/02/28", "yyyy-MM-dd", null);
        tryCatch(new HashMap<>(), "yyyy-MM-dd", null);
        tryCatch(new ArrayList<>(), "yyyy-MM-dd", null);
    }

    private void tryCatch (Object value, String format, String message) {
        try {
            DateConstraint constraint = new DateConstraint();
            constraint.setFieldName("time");
            constraint.setFormat(format);
            constraint.setMessage(message);
            new DateValidator().validate(value, constraint);
        } catch (ValidateFailedException e) {
            assertEquals("Failed to validate time: expected: " + format + ", actual: " + value, e.getMessage());
            return;
        }
        throw new RuntimeException("failed");
    }



}