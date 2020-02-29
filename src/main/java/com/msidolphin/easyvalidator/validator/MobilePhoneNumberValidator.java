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

package com.msidolphin.easyvalidator.validator;

import com.msidolphin.easyvalidator.constraint.BaseConstraint;
import com.msidolphin.easyvalidator.exception.ValidateFailedException;
import com.msidolphin.easyvalidator.util.CommonUtil;
import com.msidolphin.easyvalidator.util.RegexUtil;

public class MobilePhoneNumberValidator extends AbstractValidator<BaseConstraint> {

    private final static String PATTERN  = "^1[3456789]\\d{9}$";

    @Override
    public boolean validate(Object value, BaseConstraint constraint, String message) {
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        if (CommonUtil.isEmpty(value)) return validateFailed(value, message, getFieldName(constraint));
        if (!CommonUtil.isString(value)) return validateFailed(value, message, getFieldName(constraint));
        if (RegexUtil.test(PATTERN, (String) value)) return true;
        return validateFailed(value, message, getFieldName(constraint));
    }

    private boolean validateFailed (Object value, String message, String fieldName) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected mobile phone number, actual: " + value;
            throw new ValidateFailedException(message, fieldName);
        }
        throw new ValidateFailedException(message);
    }

}
