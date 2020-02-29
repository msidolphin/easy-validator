package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NotNullValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        new NotNullValidator().validate("", null, null);
        new NotNullValidator().validate(new HashMap<>(), null, null);
        new NotNullValidator().validate(new User(), null, null);
        new NotNullValidator().validate(new ArrayList<>(), null, null);
        new NotNullValidator().validate(new Object[]{}, null, null);
        new NotNullValidator().validate(new HashSet<>(), null, null);
        new NotNullValidator().validate(1, null, null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new NotNullValidator().validate(null, null, null); });
    }

}
