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


package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.LengthConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;

import java.util.Collection;
import java.util.Map;

public class MaxLengthValidator extends AbstractValidator<Object, LengthConstraint> {

    @Override
    public void validate(Object value, LengthConstraint constraint) {
        String message = constraint.getMessage();
        int max = constraint.getLength();
        boolean result = false;
        if (value instanceof String) {
            result = maxLength(max, (String) value);
        } else if (value instanceof Collection) {
            result = maxLength(max, (Collection) value);
        } else if (value instanceof Map) {
            result = maxLength(max, (Map) value);
        } else if (value instanceof Object[]) {
            result = maxLength(max, (Object[]) value);
        }
        if (!result) validateFailed(value, constraint, message);
    }

    private boolean maxLength(int max, String value) {
        if (value.length() > max) {
            return false;
        }
        return true;
    }

    private boolean maxLength(int max, Collection value) {
        if (value.size() > max) {
            return false;
        }
        return true;
    }

    private boolean maxLength(int max, Map value) {
        if (value.size() > max) {
            return false;
        }
        return true;
    }

    private boolean maxLength(int max, Object[] value) {
        if (value.length > max) {
            return false;
        }
        return true;
    }

    private boolean validateFailed(Object value, LengthConstraint constraint, String message) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected " + value + " length less or equal than " + constraint.getLength() + ", but actual length is " + getSize(value);
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
