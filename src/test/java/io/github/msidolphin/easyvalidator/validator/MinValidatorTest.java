package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.MinConstraint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MinValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        MinConstraint constraint = new MinConstraint();
        constraint.setMin(20);
        constraint.setFieldName("age");
        new MinValidator().validate(21, constraint);
        new MinValidator().validate(20, new MinConstraint(20));
        new MinValidator().validate(20.1, new MinConstraint(20.1));
        new MinValidator().validate(20.1112, new MinConstraint(20.1111));
        new MinValidator().validate(29.9999999999f, new MinConstraint(29.9999999999f));
        new MinValidator().validate(29.9999999999d, new MinConstraint(29.9999999999d));
        new MinValidator().validate("    ", new MinConstraint(4));
        // long
        new MinValidator().validate(12973709673103360l, new MinConstraint(12973709673103360l));
        // short
        new MinValidator().validate(Short.MAX_VALUE, new MinConstraint(Short.MAX_VALUE));
        // byte
        new MinValidator().validate(Byte.MAX_VALUE, new MinConstraint(Byte.MAX_VALUE));
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new MinValidator().validate(-2, new MinConstraint(-1)); });
        tryCatch(() -> {new MinValidator().validate(1.9999989d, new MinConstraint(1.9999990f)); });
        tryCatch(() -> {new MinValidator().validate(0.9999999f, new MinConstraint(1f)); });
        tryCatch(() -> {new MinValidator().validate("123", new MinConstraint(4)); });
        tryCatch(() -> {new MinValidator().validate(null, new MinConstraint(4)); });
        tryCatch(() -> {new MinValidator().validate(new HashMap<>(), new MinConstraint(4)); });
        tryCatch(() -> {new MinValidator().validate(new ArrayList<>(), new MinConstraint(4)); });
        tryCatch(() -> {new MinValidator().validate(98l,new MinConstraint(99l)); });
        tryCatch(() -> {new MinValidator().validate(new Byte("121"),new MinConstraint(122)); });
        tryCatch(() -> {new MinValidator().validate(new Short("121"),new MinConstraint(122)); });
        tryCatch(() -> {new MinValidator().validate(new ArrayList<>(), new MinConstraint(4)); });
    }

}
