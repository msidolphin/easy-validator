package io.github.msidolphin.easyvalidator.exception;

import junit.framework.TestCase;
import org.junit.Test;

public class ValidatorNotFoundExceptionTest extends TestCase {

    @Test
    public void test() {
        try {
            throw new ValidatorNotFoundException("aaa");
        } catch (Exception e) {
        }
    }

}