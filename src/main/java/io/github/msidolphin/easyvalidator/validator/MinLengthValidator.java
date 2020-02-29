/*
 * Copyright 2020-preset yangyu (msidolphin@outlook.com).
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


package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.LengthConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;

import java.util.Collection;
import java.util.Map;

public class MinLengthValidator extends AbstractValidator<LengthConstraint> {

    @Override
    public boolean validate(Object value, LengthConstraint constraint, String message) {
        int min = constraint.getLength();
        boolean result = false;
        if (value instanceof String) {
            result = minLength(min, (String) value);
        } else if (value instanceof Collection) {
            result = minLength(min, (Collection) value);
        } else if (value instanceof Map) {
            result = minLength(min, (Map) value);
        } else if (value instanceof Object[]) {
            result = minLength(min, (Object[]) value);
        }
        if (!result) return validateFailed(value, constraint, message);
        return true;
    }

    private boolean minLength(int min, String value) {
        if (value.length() < min) {
            return false;
        }
        return true;
    }

    private boolean minLength(int min, Collection value) {
        if (value.size() < min) {
            return false;
        }
        return true;
    }

    private boolean minLength(int min, Map value) {
        if (value.size() < min) {
            return false;
        }
        return true;
    }

    private boolean minLength(int min, Object[] value) {
        if (value.length < min) {
            return false;
        }
        return true;
    }

    private boolean validateFailed(Object value, LengthConstraint constraint, String message) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected " + value + " length greater or equal than " + constraint.getLength() + ", but actual length is " + getSize(value);
            throw new ValidateFailedException(message, constraint.getFieldName());
        }
        throw new ValidateFailedException(message);
    }

    private int getSize (Object value) {
        if (value instanceof String) {
            return ((String) value).length();
        } else if (value instanceof Collection) {
            return ((Collection) value).size();
        } else if (value instanceof Map) {
            return ((Map) value).size();
        } else if (value instanceof Object[]) {
            return ((Object[]) value).length;
        }
        return -1;
    }
    
}
