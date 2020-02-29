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
        if ((o instanceof List)) {
            return ((List) o).size() == 0;
        }
        if ((o instanceof Set)) {
            return ((Set) o).size() == 0;
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
