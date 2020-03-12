package io.github.msidolphin.easyvalidator.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MobilePhoneNumberValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        new MobilePhoneNumberValidator().validate("13600000000", null);
        new MobilePhoneNumberValidator().validate("14600000000", null);
        new MobilePhoneNumberValidator().validate("15600000000", null);
        new MobilePhoneNumberValidator().validate("16600000000", null);
        new MobilePhoneNumberValidator().validate("17600000000", null);
        new MobilePhoneNumberValidator().validate("18600000000", null);
        new MobilePhoneNumberValidator().validate("19600000000", null);
    }

    @Test
    public void testFailed () {
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("136000000001", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("236000000001", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("116000000001", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("126000000001", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate(null, null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate("213123123asdasdas", null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate(new HashMap<>(), null); });
        tryCatch(() -> {new MobilePhoneNumberValidator().validate(new ArrayList<>(), null); });
    }

}
