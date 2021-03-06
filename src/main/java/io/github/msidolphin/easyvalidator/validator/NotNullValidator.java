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

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import io.github.msidolphin.easyvalidator.exception.ValidateFailedException;
import io.github.msidolphin.easyvalidator.util.CommonUtil;

public class NotNullValidator extends AbstractValidator<Object, BaseConstraint> {

    @Override
    public void validate(Object value, BaseConstraint constraint) {
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        String message = constraint.getMessage();
        if (value != null) return;
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        if (CommonUtil.isEmpty(message)) {
            message = "expected not null";
            throw new ValidateFailedException(message, constraint.getFieldName());
        }
        throw new ValidateFailedException(message);
    }

}
