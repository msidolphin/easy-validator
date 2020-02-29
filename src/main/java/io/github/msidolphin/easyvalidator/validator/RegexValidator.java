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

import io.github.msidolphin.easyvalidator.constraint.RegexConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;
import io.github.msidolphin.easyvalidator.util.RegexUtil;

public class RegexValidator extends AbstractValidator<RegexConstraint> {

    @Override
    public boolean validate(Object value, RegexConstraint constraint, String message) {
        if (!CommonUtil.isString(value)) return false;
        if (CommonUtil.isEmpty(constraint)) return false;
        String pattern = constraint.getPattern();
        if (!RegexUtil.test(pattern, (String) value)) {
            if (CommonUtil.isEmpty(message)) {
                message = "regular expression matching failed, pattern: "
                        + constraint.getPattern()
                        + ", value: " + value;
                throw new ValidateFailedException(message, constraint.getFieldName());
            }
            throw new ValidateFailedException(message);
        }
        return true;
    }

}
