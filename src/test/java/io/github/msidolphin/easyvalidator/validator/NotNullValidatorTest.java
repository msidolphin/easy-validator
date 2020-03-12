package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NotNullValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        new NotNullValidator().validate("", null);
        new NotNullValidator().validate(new HashMap<>(), null);
        new NotNullValidator().validate(new User(), null);
        new NotNullValidator().validate(new ArrayList<>(), null);
        new NotNullValidator().validate(new Object[]{}, null);
        new NotNullValidator().validate(new HashSet<>(), null);
        new NotNullValidator().validate(1, null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new NotNullValidator().validate(null, null); });
    }

}
