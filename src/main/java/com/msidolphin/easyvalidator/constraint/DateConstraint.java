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

package com.msidolphin.easyvalidator.constraint;

public class DateConstraint extends BaseConstraint {

    private String format = "yyyy-MM-dd";

    public DateConstraint() {}

    public DateConstraint(String format) {
        this.format = format;
    }

    public DateConstraint(String format, String fieldName) {
        super(fieldName);
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
