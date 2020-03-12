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

import io.github.msidolphin.easyvalidator.constraint.MinConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;

public class MinValidator extends AbstractValidator<Object, MinConstraint> {

    @Override
    public void validate(Object value, MinConstraint constraint) {
        String message = constraint.getMessage();
        Number min = constraint.getMin();
        if (CommonUtil.isEmpty(value) && !(value instanceof String)) {
            if (CommonUtil.isEmpty(message)) throw new ValidateFailedException(getMessage(value, min, message), constraint.getFieldName());
            else throw new ValidateFailedException(message);
        }
        boolean result = false;
        if (value instanceof Integer) {
            result = min(min.intValue(), (Integer) value);
        } else if (value instanceof Long) {
            result = min(min.longValue(), (Long) value);
        } else if (value instanceof Double) {
            result = min(min.doubleValue(), (Double) value);
        } else if (value instanceof Float) {
            result = min(min.floatValue(), (Float) value);
        } else if (value instanceof Short) {
            result = min(min.shortValue(), (Short) value);
        } else if (value instanceof Byte) {
            result = min(min.byteValue(), (Byte) value);
        } else if (value instanceof String) {
            result = min(min.intValue(), ((String) value).length());
        }
        if (!result) {
            if (CommonUtil.isEmpty(message)) throw new ValidateFailedException(getMessage(value, min, message), constraint.getFieldName());
            else throw new ValidateFailedException(message);
        }
    }

    private String getMessage (Object value, Number min, String message) {
        if (CommonUtil.isNotEmpty(message)) return message;
        if (value instanceof String) {
            return "expected " + value + " length greater or equal than " + min;
        }
        return "expected " + value + " greater or equal than " + min;
    }

    private static boolean min(int min, int value) {
        if (value < min) {
            return false;
        }
        return true;
    }

    private static boolean min(long min, long value) {
        if (value < min) {
            return false;
        }
        return true;
    }

    private static boolean min(float min, float value) {
        if (value < min) {
            return false;
        }
        return true;
    }

    private static boolean min(double min, double value) {
        if (value < min) {
            return false;
        }
        return true;
    }

    private static boolean min(byte min, byte value) {
        if (value < min) {
            return false;
        }
        return true;
    }

    private static boolean min(short min, short value) {
        if (value < min) {
            return false;
        }
        return true;
    }
    
}
