package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.RegexConstraint;
import org.junit.Test;

public class RegexValidatorTest extends BaseValidatorTest {

    private String PATTERN = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)";


    @Test
    public void test() {
        RegexConstraint constraint = new RegexConstraint();
        constraint.setPattern(PATTERN);
        constraint.setFieldName("ip");
        new RegexValidator().validate("192.168.0.1", constraint);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {
            new RegexValidator().validate("192.168.0.256", new RegexConstraint(PATTERN));
        });
    }

}
