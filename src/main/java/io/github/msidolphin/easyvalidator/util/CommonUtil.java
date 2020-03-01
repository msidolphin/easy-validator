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

package io.github.msidolphin.easyvalidator.util;

import java.util.*;

public class CommonUtil {

    public static boolean isString(Object o) {
        if (o == null) return false;
        return o instanceof String;
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if ((o instanceof String)) {
            return ((String) o).trim().length() == 0;
        }
        if ((o instanceof Collection)) {
            return ((Collection) o).size() == 0;
        }
        if ((o instanceof Map)) {
            return ((Map) o).size() == 0;
        }
        if ((o instanceof Object[])) {
            return ((Object[]) o).length == 0;
        }

        return false;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

}
