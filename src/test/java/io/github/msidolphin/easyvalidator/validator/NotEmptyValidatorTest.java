package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.bean.User;
import org.junit.Test;

import java.util.*;

public class NotEmptyValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        new NotEmptyValidator().validate("abd", null);
        new NotEmptyValidator().validate(1, null);
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        new NotEmptyValidator().validate(map, null);
        List<String> list = new ArrayList<>();
        list.add("1");
        new NotEmptyValidator().validate(list, null);
        User user = new User();
        new NotEmptyValidator().validate(user, null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new NotEmptyValidator().validate(null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate("", null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new HashMap<>(), null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new ArrayList<>(), null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new Object[]{}, null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new HashSet<>(), null); });
    }

}
