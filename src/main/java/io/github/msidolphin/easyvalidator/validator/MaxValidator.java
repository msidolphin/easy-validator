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

import io.github.msidolphin.easyvalidator.constraint.MaxConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;

public class MaxValidator extends AbstractValidator<Object, MaxConstraint> {

    @Override
    public void validate(Object value, MaxConstraint constraint) {
        String message = constraint.getMessage();
        Number max = constraint.getMax();
        if (CommonUtil.isEmpty(value) && !(value instanceof String)) {
            if (CommonUtil.isEmpty(message)) throw new ValidateFailedException(getMessage(value, max, message), constraint.getFieldName());
            else throw new ValidateFailedException(message);
        }
        boolean result = false;
        if (value instanceof Integer) {
            result = max(max.intValue(), (Integer) value);
        } else if (value instanceof Long) {
            result = max(max.longValue(), (Long) value);
        } else if (value instanceof Double) {
            result = max(max.doubleValue(), (Double) value);
        } else if (value instanceof Float) {
            result = max(max.floatValue(), (Float) value);
        } else if (value instanceof Short) {
            result = max(max.shortValue(), (Short) value);
        } else if (value instanceof Byte) {
            result = max(max.byteValue(), (Byte) value);
        } else if (value instanceof String) {
            result = max(max.intValue(), ((String) value).length());
        }
        if (!result) {
            if (CommonUtil.isEmpty(message)) throw new ValidateFailedException(getMessage(value, max, message), constraint.getFieldName());
            else throw new ValidateFailedException(message);
        }
    }

    private String getMessage (Object value, Number max, String message) {
        if (CommonUtil.isNotEmpty(message)) return message;
        if (value instanceof String) {
            return "expected " + value + " length less or equal than " + max;
        }
        return "expected " + value + " less or equal than " + max;
    }

    private static boolean max(int max, int value) {
        if (value > max) {
            return false;
        }
        return true;
    }

    private static boolean max(long max, long value) {
        if (value > max) {
            return false;
        }
        return true;
    }

    private static boolean max(float max, float value) {
        if (value > max) {
            return false;
        }
        return true;
    }

    private static boolean max(double max, double value) {
        if (value > max) {
            return false;
        }
        return true;
    }

    private static boolean max(byte max, byte value) {
        if (value > max) {
            return false;
        }
        return true;
    }

    private static boolean max(short max, short value) {
        if (value > max) {
            return false;
        }
        return true;
    }

}
