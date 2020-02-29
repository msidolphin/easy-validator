package com.msidolphin.easyvalidator.validator;

import com.msidolphin.easyvalidator.constraint.BaseConstraint;
import com.msidolphin.easyvalidator.exception.ValidateFailedException;
import junit.framework.TestCase;
import org.junit.Test;

public class BaseValidatorTest extends TestCase {

    private class BaseValidator extends AbstractValidator {}

    @Test
    public void test() {
        AbstractValidator validator = new BaseValidator();
        validator.validate(null, null, null);
        validator.getFieldName(new BaseConstraint("a"));
    }

    protected void tryCatch (Runnable runnable) {
        try {
            runnable.run();
        } catch (ValidateFailedException e) {
            System.out.println(e);
            return;
        }
        throw new RuntimeException("failed");
    }
}
