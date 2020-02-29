package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.bean.User;
import org.junit.Test;

import java.util.*;

public class NotEmptyValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        new NotEmptyValidator().validate("abd", null, null);
        new NotEmptyValidator().validate(1, null, null);
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        new NotEmptyValidator().validate(map, null, null);
        List<String> list = new ArrayList<>();
        list.add("1");
        new NotEmptyValidator().validate(list, null, null);
        User user = new User();
        new NotEmptyValidator().validate(user, null, null);
    }

    @Test
    public void testFailed() {
        tryCatch(() -> {new NotEmptyValidator().validate(null, null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate("", null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new HashMap<>(), null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new ArrayList<>(), null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new Object[]{}, null, null); });
        tryCatch(() -> {new NotEmptyValidator().validate(new HashSet<>(), null, null); });
    }

}
