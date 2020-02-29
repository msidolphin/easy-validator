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
import io.github.msidolphin.easyvalidator.util.RegexUtil;

public class IpValidator extends AbstractValidator<BaseConstraint> {

    private final static String IPV4_PATTERN = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)";

    private final static String IPV6_PATTERN = "(^(([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}{1}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}{1}|((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}{1,2}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}{1,3}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}{1,4}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}{1,5}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(([0-9A-Fa-f]{1,4}:){1}(:[0-9A-Fa-f]{1,4}{1,6}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))|(:(:[0-9A-Fa-f]{1,4}{1,7}|:((22[0-3]丨2[0-1][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})([.](25[0-5]|2[0-4][0-9]|[0-1][0-9][0-9]|0[1 -9][0-9]|([0-9])]{1,2})){3})|:))$)";

    @Override
    public boolean validate(Object value, BaseConstraint constraint, String message) {
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        if (CommonUtil.isEmpty(value))  return validateFailed(value, message, getFieldName(constraint));
        if (!CommonUtil.isString(value))  return validateFailed(value, message, getFieldName(constraint));
        if (RegexUtil.test(IPV4_PATTERN, (String) value) || RegexUtil.test(IPV6_PATTERN, (String) value)) return true;
        return validateFailed(value, message, getFieldName(constraint));
    }

    private boolean validateFailed (Object value, String message, String fieldName) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected ip address, actual: " + value;
            throw new ValidateFailedException(message, fieldName);
        }
        throw new ValidateFailedException(message);
    }


}
