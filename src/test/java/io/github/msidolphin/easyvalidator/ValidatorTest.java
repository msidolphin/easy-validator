/*
 * Copyright 2020-present yangyu (msidolphin@outlook.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.msidolphin.easyvalidator;

import io.github.msidolphin.easyvalidator.bean.EmptyEntity;
import io.github.msidolphin.easyvalidator.bean.User;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.validator.BaseValidatorTest;

import org.junit.Test;

import java.util.*;

public class ValidatorTest extends BaseValidatorTest {

    @Test
    public void test() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "mike");
        params.put("age", 25);
        User user = new User();
        user.setName("mike")
            .setAge(25)
            .setEmail("validator@vip.com")
            .setPhoneNumber("18600000000")
            .setBirthday("1995-01-01")
            .setIdCard("11010119950101555X")
            .setIp("192.168.0.1")
            .setIntro("我的名字叫迈克")
            .setWeight("75kg");
        Validator.is(params).notNull()
                .get("name").notEmpty().english().maxLength(10)
                .get("age").notEmpty().min(18).max(26)
                .and(user).notNull()
                .get("name").notEmpty().english().maxLength(10)
                .get("age").notEmpty().min(18).max(26)
                .get("email").email()
                .get("phoneNumber").phone()
                .get("birthday").date("yyyy-MM-dd")
                .get("ip").ip()
                .get("idCard").idCard()
                .get("intro").chinese().minLength(2)
                .get("weight").regex("\\d{2,3}kg");
    }

    @Test
    public void testAnnotation() {
        User user = new User();
        user.setName("mike")
                .setAge(25)
                .setEmail("validator@vip.com")
                .setPhoneNumber("18600000000")
                .setBirthday("1995-01-01")
                .setIdCard("11010119950101555X")
                .setIp("192.168.0.1")
                .setIntro("我的名字叫迈克")
                .setWeight("75kg");
        Validator.validate(user);
    }

    @Test
    public void testAnnotationFailed() {
        //  name
        {
            // @NotEmpty
            tryCatch(() -> {
                User user = new User();
                Validator.validate(user);
            });
            // @MaxLength
            tryCatch(() -> {
                User user = new User();
                user.setName("mikemikemikemike");
                Validator.validate(user);
            });
            // @English
            tryCatch(() -> {
                User user = new User();
                user.setName("迈克");
                Validator.validate(user);
            });
        }
        // age
        {
            // @NotNull
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                Validator.validate(user);
            });
            // @Min
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(17);
                Validator.validate(user);
            });
            // @Max
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(27);
                Validator.validate(user);
            });
        }
        // phone
        {
            // @Phone
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("186");
                Validator.validate(user);
            });
            // @NotEmpty
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                Validator.validate(user);
            });
        }
        // email
        {
            // @Email
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setEmail("...validator@vip.com");
                Validator.validate(user);
            });
        }
        // intro
        {
            // @Chinese
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setIntro("hello");
                Validator.validate(user);
            });
            // @MaxLength
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setIntro("嗨！我的名字叫迈克，我来自美国，我是一名前端工程师。");
                Validator.validate(user);
            });
            // @MinLength
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setIntro("嗨");
                Validator.validate(user);
            });
        }
        // id card
        {
            // @IdCard
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setIdCard("910101199003073917");
                Validator.validate(user);
            });
        }
        // ip
        {
            // @IP
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setIp("192.168.0.256");
                Validator.validate(user);
            });
        }
        // birthday
        {
            // @Date
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setBirthday("1995/01/01");
                Validator.validate(user);
            });
        }
        // weight
        {
            // @Pattern
            tryCatch(() -> {
                User user = new User();
                user.setName("mike");
                user.setAge(26);
                user.setPhoneNumber("18600000000");
                user.setWeight("165lb");
                Validator.validate(user);
            });
        }
    }

    @Test
    public void testEntity() {
        EmptyEntity entity = new EmptyEntity();
        Validator.validate(entity);
        try {
            Validator.is(entity).get("name").notEmpty();
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGet () {
        Validator.validate(1);
    }

}
