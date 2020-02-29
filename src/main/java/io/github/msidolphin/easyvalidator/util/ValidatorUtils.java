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

package io.github.msidolphin.easyvalidator.util;

import io.github.msidolphin.easyvalidator.constraint.DateConstraint;
import io.github.msidolphin.easyvalidator.validator.*;

import java.util.concurrent.ConcurrentHashMap;

public class ValidatorUtils {

    private static final ConcurrentHashMap<Class, IValidator> validators =
            new ConcurrentHashMap<>();

    static {
        register(new ChineseValidator(), ChineseValidator.class);
        register(new DateValidator(), DateConstraint.class);
        register(new EmailValidator(), EmailValidator.class);
        register(new EnglishValidator(), EnglishValidator.class);
        register(new IdCardValidator(), IdCardValidator.class);
        register(new IpValidator(), IpValidator.class);
        register(new MaxLengthValidator(), MaxLengthValidator.class);
        register(new MaxValidator(), MaxValidator.class);
        register(new MinLengthValidator(), MinLengthValidator.class);
        register(new MinValidator(), MinValidator.class);
        register(new MobilePhoneNumberValidator(), MobilePhoneNumberValidator.class);
        register(new NotEmptyValidator(), NotEmptyValidator.class);
        register(new NotNullValidator(), NotNullValidator.class);
        register(new RegexValidator(), RegexValidator.class);
    }

    public static void register(final IValidator validator, final Class clazz) {
        validators.put(clazz, validator);
    }

    @SuppressWarnings("all")
    public static  <T extends IValidator> T lookup(final Class clazz) {
        IValidator validator = validators.get(clazz);
        if (validator == null) {
            try {
                validator = (IValidator) clazz.newInstance();
                register(validator, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) validator;
    }

}
