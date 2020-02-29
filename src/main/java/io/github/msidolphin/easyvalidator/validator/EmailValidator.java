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

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;
import io.github.msidolphin.easyvalidator.util.RegexUtil;

public class EmailValidator extends AbstractValidator<BaseConstraint> {

    private static String PATTERN = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    @Override
    public boolean validate(Object value, BaseConstraint constraint, String message) {
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        if (CommonUtil.isEmpty(value)) return validateFailed(value, message, constraint.getFieldName());
        if (!CommonUtil.isString(value)) return validateFailed(value, message, constraint.getFieldName());
        if (RegexUtil.test(PATTERN, (String) value)) return true;
        return validateFailed(value, message, constraint.getFieldName());
    }

    private boolean validateFailed (Object value, String message, String filedName) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected email, actual: " + value;
            throw new ValidateFailedException(message, filedName);
        }
        throw new ValidateFailedException(message);
    }

}
