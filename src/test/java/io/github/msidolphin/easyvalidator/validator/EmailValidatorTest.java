package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class EmailValidatorTest extends TestCase {

    @Test
    public void test() {
        new EmailValidator().validate("1234123412341@outlook.com", null);
        new EmailValidator().validate("iqweopriopqewrk@sinochem.com", null);
        new EmailValidator().validate("ajkldsfjkladsjklf@vip.tom.com", null);
    }

    @Test
    public void testFailed() {
        tryCatch(null, "please enter email");
        tryCatch("", null);
        tryCatch(1, null);
        tryCatch("1111111", null);
        tryCatch("鑫森淼焱垚犇骉羴猋麤@hotmail.com", null);
        tryCatch(new HashMap<>(), null);
        tryCatch(new ArrayList<>(), null);
    }

    private void tryCatch(Object value, String message) {
        try {
            BaseConstraint constraint = new BaseConstraint();
            constraint.setMessage(message);
            new EmailValidator().validate(value, constraint);
        } catch (ValidateFailedException e) {
            System.out.println(e);
            return;
        }
        throw new RuntimeException("failed");
    }

}
