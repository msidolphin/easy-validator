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

import io.github.msidolphin.easyvalidator.constraint.*;
import io.github.msidolphin.easyvalidator.util.CommonUtil;
import io.github.msidolphin.easyvalidator.util.ReflectUtils;
import io.github.msidolphin.easyvalidator.util.ValidateCache;
import io.github.msidolphin.easyvalidator.util.ValidatorUtils;
import io.github.msidolphin.easyvalidator.annotation.*;
import io.github.msidolphin.easyvalidator.validator.*;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Validator
 */
public final class Validator {

    private Object value;

    private Object currentValue;

    private String filedName;

    private Validator(Object value) {
        this.value = value;
        this.currentValue = value;
        this.filedName = null;
    }

    public static Validator is(Object value) {
        return new Validator(value);
    }

    public Validator get(String fieldName) {
        try {
            this.currentValue = PropertyUtils.getProperty(this.value, fieldName);
            this.filedName = fieldName;
            return this;
        } catch (Exception e) {
            this.currentValue = null;
        }
        return this;
    }

    private boolean isEmpty () {
        return CommonUtil.isEmpty(this.currentValue);
    }

    public Validator and(Object value) {
        this.value = value;
        this.currentValue = value;
        this.filedName = null;
        return this;
    }

    public Validator notNull() {
        return notNull(null);
    }

    public Validator notEmpty () {
        return notEmpty(null);
    }

    public Validator notEmpty (String message) {
        NotEmptyValidator validator = ValidatorUtils.lookup(NotEmptyValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator notNull(String message) {
        NotNullValidator validator = ValidatorUtils.lookup(NotNullValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator regex(String regex) {
        return regex(regex, null);
    }

    public Validator regex(String regex, String message) {
        RegexValidator validator = ValidatorUtils.lookup(RegexValidator.class);
        validator.validate(currentValue, new RegexConstraint(regex, filedName), message);
        return this;
    }

    public Validator max(Number max) {
        return max(max, null);
    }

    public Validator max(Number max, String message) {
        MaxValidator validator = ValidatorUtils.lookup(MaxValidator.class);
        validator.validate(currentValue, new MaxConstraint(max, filedName), message);
        return this;
    }

    public Validator min(Number min) {
        return min(min, null);
    }

    public Validator min(Number min, String message) {
        MinValidator validator = ValidatorUtils.lookup(MinValidator.class);
        validator.validate(currentValue, new MinConstraint(min, filedName), message);
        return this;
    }

    public Validator maxLength(int length) {
        return maxLength(length, null);
    }

    public Validator maxLength(int length, String message) {
        MaxLengthValidator validator = ValidatorUtils.lookup(MaxLengthValidator.class);
        validator.validate(currentValue, new LengthConstraint(length, filedName), message);
        return this;
    }

    public Validator minLength(int length) {
        return minLength(length, null);
    }

    public Validator minLength(int length, String message) {
        MinLengthValidator validator = ValidatorUtils.lookup(MinLengthValidator.class);
        validator.validate(currentValue, new LengthConstraint(length, filedName), message);
        return this;
    }

    public Validator chinese() {
        return chinese(null);
    }

    public Validator chinese(String message) {
        ChineseValidator validator = ValidatorUtils.lookup(ChineseValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator english() {
        return english(null);
    }

    public Validator english(String message) {
        EnglishValidator validator = ValidatorUtils.lookup(EnglishValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator phone() {
        return phone(null);
    }

    public Validator phone(String message) {
        MobilePhoneNumberValidator validator = ValidatorUtils.lookup(MobilePhoneNumberValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator email() {
        return email(null);
    }

    public Validator email(String message) {
        EmailValidator validator = ValidatorUtils.lookup(EmailValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator date(String format) {
        return date(format, null);
    }

    public Validator date(String format, String message) {
        DateValidator validator = ValidatorUtils.lookup(DateValidator.class);
        validator.validate(currentValue, new DateConstraint(format, filedName), message);
        return this;
    }


    public Validator idCard() {
        return idCard(null);
    }

    public Validator idCard(String message) {
        IdCardValidator validator = ValidatorUtils.lookup(IdCardValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public Validator ip() {
        return ip(null);
    }

    public Validator ip(String message) {
        IpValidator validator = ValidatorUtils.lookup(IpValidator.class);
        validator.validate(currentValue, new BaseConstraint(filedName), message);
        return this;
    }

    public static Validator validate(Object value) {
        Validator validator = new Validator(value);
        validator.notNull();
        Class classType = value.getClass();
        List<Field> fieldSet = ValidateCache.getInstance().getFieldsByClass(classType);
        if (null == fieldSet) {
            fieldSet = ReflectUtils.getFieldsByClass(value.getClass());
            ValidateCache.getInstance().setClassFields(classType, fieldSet);
        }
        if (CommonUtil.isEmpty(fieldSet)) {
            return validator;
        }
        for (Field field : fieldSet) {
            Annotation[] annotations = ValidateCache.getInstance().getAnnotationsByField(field);
            if (null == annotations) {
                annotations = field.getAnnotations();
                ValidateCache.getInstance().setFieldAnnotations(field, annotations);
            }
            if (CommonUtil.isEmpty(annotations)) {
                continue;
            }
            try {
                validator.get(field.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (Annotation annotation : annotations) {

                if (annotation instanceof NotNull) {
                    validator.notNull(((NotNull) annotation).msg());
                    continue;
                }
                if (annotation instanceof NotEmpty) {
                    validator.notEmpty(((NotEmpty) annotation).msg());
                    continue;
                }
                if (validator.isEmpty()) continue;
                if (annotation instanceof Max) {
                    Max max = (Max) annotation;
                    validator.max(max.value(), max.msg());
                } else if (annotation instanceof Min) {
                    Min min = (Min) annotation;
                    validator.min(min.value(), min.msg());
                } else if (annotation instanceof MaxLength) {
                    MaxLength maxLength = (MaxLength) annotation;
                    validator.maxLength(maxLength.value(), maxLength.msg());
                } else if (annotation instanceof MinLength) {
                    MinLength minLength = (MinLength) annotation;
                    validator.minLength(minLength.value(), minLength.msg());
                } else if (annotation instanceof Email) {
                    validator.email(((Email) annotation).msg());
                } else if (annotation instanceof Phone) {
                    validator.phone(((Phone) annotation).msg());
                } else if (annotation instanceof IdCard) {
                    validator.idCard(((IdCard) annotation).msg());
                } else if (annotation instanceof Pattern) {
                    Pattern regex = (Pattern) annotation;
                    validator.regex(regex.value(), regex.msg());
                } else if (annotation instanceof Date) {
                    Date date = (Date) annotation;
                    String format = date.value();
                    validator.date(format, date.msg());
                } else if (annotation instanceof Chinese) {
                    validator.chinese(((Chinese) annotation).msg());
                } else if (annotation instanceof English) {
                    validator.english(((English) annotation).msg());
                } else if (annotation instanceof IP) {
                    validator.ip(((IP) annotation).msg());
                }
            }
        }
        return validator;
    }

}
