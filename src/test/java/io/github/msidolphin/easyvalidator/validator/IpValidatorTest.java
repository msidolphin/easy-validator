package io.github.msidolphin.easyvalidator.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class IpValidatorTest extends BaseValidatorTest  {

    @Test
    public void test() {
        // ipv4
        new IpValidator().validate("192.168.0.1", null);
        new IpValidator().validate("0.0.0.0", null);
        new IpValidator().validate("127.0.0.1", null);
        new IpValidator().validate("255.255.255.255", null);
        new IpValidator().validate("163.179.125.37", null);
        // ipv6
        new IpValidator().validate("2001:0db8:85a3:0000:0000:8a2e:0370:7334", null);
    }

    @Test
    public void testFailed () {
        tryCatch(() -> {new IpValidator().validate("192.168.0.256", null);});
        tryCatch(() -> {new IpValidator().validate("192.168.256.1", null); });
        tryCatch(() -> {new IpValidator().validate("192.256.0.1", null); });
        tryCatch(() -> {new IpValidator().validate("256.168.0.256", null); });
        tryCatch(() -> {new IpValidator().validate(null, null); });
        tryCatch(() -> {new IpValidator().validate("", null); });
        tryCatch(() -> {new IpValidator().validate("12341324asdfasdf", null); });
        tryCatch(() -> {new IpValidator().validate(new HashMap<>(), null); });
        tryCatch(() -> {new IpValidator().validate(new ArrayList<>(), null); });
    }

}
