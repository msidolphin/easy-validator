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
import io.github.msidolphin.easyvalidator.util.CommonUtil;

public abstract class AbstractValidator<E, T extends BaseConstraint> implements IValidator<E, T> {

    @Override
    public void validate(E value, T constraint) {

    }

    public String getFieldName (T constraint) {
        if (CommonUtil.isEmpty(constraint)) return null;
        return constraint.getFieldName();
    }

}
