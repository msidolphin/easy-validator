package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class EnglishValidatorTest extends BaseValidatorTest {

    @Test
    public void test () {
        new EnglishValidator().validate("Iamfinethankyouandyou", new BaseConstraint());
        new EnglishValidator().validate("abcDEfgHijklmnoPqrStuvWxYz", new BaseConstraint());
    }

    @Test
    public void testFailed () {
        tryCatch(() -> {new EnglishValidator().validate(null, new BaseConstraint()); });
        tryCatch(() -> {new EnglishValidator().validate("", new BaseConstraint()); });
        tryCatch(() -> {new EnglishValidator().validate("？？？？？？", new BaseConstraint()); });
        tryCatch(() -> {new EnglishValidator().validate(new HashMap<>(), new BaseConstraint()); });
        tryCatch(() -> {new EnglishValidator().validate(new ArrayList<>(), new BaseConstraint()); });
    }


}
