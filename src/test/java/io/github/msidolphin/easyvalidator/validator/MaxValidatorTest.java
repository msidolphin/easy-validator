package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.MaxConstraint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MaxValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        MaxConstraint constraint = new MaxConstraint();
        constraint.setFieldName("age");
        constraint.setMax(20);
        new MaxValidator().validate(10, constraint, null);
        new MaxValidator().validate(20, new MaxConstraint(20), null);
        new MaxValidator().validate(20, new MaxConstraint(20.1), null);
        new MaxValidator().validate(20.1111, new MaxConstraint(20.1111), null);
        new MaxValidator().validate(29.9999999999f, new MaxConstraint(29.9999999999f), null);
        new MaxValidator().validate(29.9999999999d, new MaxConstraint(29.9999999999d), null);
        new MaxValidator().validate("", new MaxConstraint(4), null);
        // long
        new MaxValidator().validate(12973709673103360l, new MaxConstraint(12973709673103360l), null);
        // short
        new MaxValidator().validate(Short.MAX_VALUE, new MaxConstraint(Short.MAX_VALUE), null);
        // byte
        new MaxValidator().validate(Byte.MAX_VALUE, new MaxConstraint(Byte.MAX_VALUE), null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new MaxValidator().validate(1, new MaxConstraint(-1), null); });
        tryCatch(() -> {new MaxValidator().validate(1.9999991d, new MaxConstraint(1.9999990f), null); });
        tryCatch(() -> {new MaxValidator().validate(1.9999991f, new MaxConstraint(1f), null); });
        tryCatch(() -> {new MaxValidator().validate("12345", new MaxConstraint(4), null); });
        tryCatch(() -> {new MaxValidator().validate(null, new MaxConstraint(4), null); });
        tryCatch(() -> {new MaxValidator().validate(new HashMap<>(), new MaxConstraint(4), null); });
        tryCatch(() -> {new MaxValidator().validate(new ArrayList<>(), new MaxConstraint(4), null); });
        tryCatch(() -> {new MaxValidator().validate(100l,new MaxConstraint(99l), null); });
        tryCatch(() -> {new MaxValidator().validate(new Byte("123"),new MaxConstraint(122), null); });
        tryCatch(() -> {new MaxValidator().validate(new Short("123"),new MaxConstraint(122), null); });
        tryCatch(() -> {new MaxValidator().validate(new ArrayList<>(), new MaxConstraint(4), null); });
    }

}
