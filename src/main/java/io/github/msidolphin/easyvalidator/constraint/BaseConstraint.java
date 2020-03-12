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

package io.github.msidolphin.easyvalidator.constraint;

public class BaseConstraint {

    private String message;

    private String fieldName;

    public BaseConstraint() {
    }

    public BaseConstraint(String fieldName) {
        this.fieldName = fieldName;
    }

    public BaseConstraint(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public BaseConstraint setMessage(String message) {
        this.message = message;
        return this;
    }

    public static BaseConstraint createConstraint(String fieldName, String message) {
        BaseConstraint constraint = new BaseConstraint(fieldName, message);
        return constraint;
    }

}
