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
        new DateValidator().validate("2020-02-28", new DateConstraint("yyyy-MM-dd"), null);
        new DateValidator().validate("2020.02.28", new DateConstraint("yyyy.MM.dd"), null);
        new DateValidator().validate("2020年02月28日", new DateConstraint("yyyy年MM月dd日"), null);
        new DateValidator().validate("2020/02/28", new DateConstraint("yyyy/MM/dd"), null);
        new DateValidator().validate("2020年02月28日 11:14:30", new DateConstraint("yyyy年MM月dd日 HH:mm:ss"), null);
        new DateValidator().validate("2020年02月28日 11:14", new DateConstraint("yyyy年MM月dd日 HH:mm"), null);
        new DateValidator().validate("2020年02月28日 11", new DateConstraint("yyyy年MM月dd日 HH"), null);
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
            new DateValidator().validate(value, new DateConstraint(format), message);
        } catch (ValidateFailedException e) {
            System.out.println(e);
            return;
        }
        throw new RuntimeException("failed");
    }



}