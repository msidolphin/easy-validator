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

import java.util.HashMap;
import java.util.Map;

public class IdCardValidator extends AbstractValidator<BaseConstraint> {

    private final static int[] factorArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    private final static char[] parityBit = {'1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'};
    private final static String REGEX_AREA = "^[0-9]{2}$";
    private final static String REGEX_DATE8 = "^[0-9]{8}$";

    private final static Map<Integer, String> areaCode = new HashMap<>();

    private final static int MIN_YEAR = 1700;
    private final static int MAX_YEAR = 2500;

    static {
        areaCode.put(11, "北京");
        areaCode.put(12, "天津");
        areaCode.put(13, "河北");
        areaCode.put(14, "山西");
        areaCode.put(15, "内蒙古");
        areaCode.put(21, "辽宁");
        areaCode.put(22, "吉林");
        areaCode.put(23, "黑龙江");
        areaCode.put(31, "上海");
        areaCode.put(32, "江苏");
        areaCode.put(33, "浙江");
        areaCode.put(34, "安徽");
        areaCode.put(35, "福建");
        areaCode.put(36, "江西");
        areaCode.put(37, "山东");
        areaCode.put(41, "河南");
        areaCode.put(42, "湖北");
        areaCode.put(43, "湖南");
        areaCode.put(44, "广东");
        areaCode.put(45, "广西");
        areaCode.put(46, "海南");
        areaCode.put(50, "重庆");
        areaCode.put(51, "四川");
        areaCode.put(52, "贵州");
        areaCode.put(53, "云南");
        areaCode.put(54, "西藏");
        areaCode.put(61, "陕西");
        areaCode.put(62, "甘肃");
        areaCode.put(63, "青海");
        areaCode.put(64, "新疆");
        areaCode.put(71, "台湾");
        areaCode.put(81, "香港");
        areaCode.put(82, "澳门");
    }

    @Override
    public boolean validate(Object value, BaseConstraint constraint,  String message) {
        if (CommonUtil.isEmpty(constraint)) constraint = new BaseConstraint();
        if (CommonUtil.isEmpty(value)) return validateFailed(value, message, getFieldName(constraint));
        if (!CommonUtil.isString(value)) return validateFailed(value, message, getFieldName(constraint));
        if (isIdCard((String) value)) return true;
        return validateFailed(value, message, getFieldName(constraint));
    }

    private boolean validateFailed (Object value, String message, String fieldName) {
        if (CommonUtil.isEmpty(message)) {
            message = "expected china ID card : " + value;
            throw new ValidateFailedException(message, fieldName);
        }
        throw new ValidateFailedException(message);
    }

    /**
     * 身份证15位编码规则：dddddd yymmdd xx p
     * dddddd：地区码
     * yymmdd: 出生年月日
     * xx: 顺序类编码
     * p: 性别，奇数为男，偶数为女
     * <p/>
     * 身份证18位编码规则：dddddd yyyymmdd xxx y
     * dddddd：地区码
     * yyyymmdd: 出生年月日
     * xxx:顺序类编码，奇数为男，偶数为女
     * y: 校验码，该位数值可通过前17位计算获得
     * <p/>
     * 18位号码加权因子为(从右到左) wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]
     * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
     * 校验位计算公式：Y_P = mod( ∑(Ai×wi),11 )
     * i为身份证号码从右往左数的 2...18 位; Y_P为校验码所在校验码数组位置
     */
    private static boolean isIdCard(String value) {
        if (CommonUtil.isEmpty(value)) return false;
        String idCard = value.toLowerCase();
        int length = idCard.length();
        //校验位数
        if (length != 15 && length != 18) {
            return false;
        }
        //校验区域
        if (!isArea(idCard.substring(0, 2))) {
            return false;
        }
        //校验日期
        if (15 == length && !isDate6(idCard.substring(6, 12))) {
            return false;
        }
        if (18 == length && !isDate8(idCard.substring(6, 14))) {
            return false;
        }
        //校验18位校验码
        if (18 == length) {
            char[] idCardArray = idCard.toCharArray();
            int sum = 0;
            for (int i = 0; i < idCardArray.length - 1; i++) {
                if (idCardArray[i] < '0' || idCardArray[i] > '9') {
                    return false;
                }
                sum += (idCardArray[i] - '0') * factorArr[i];
            }
            if (idCardArray[idCardArray.length - 1] != parityBit[sum % 11]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isArea(String area) {
        return RegexUtil.test(REGEX_AREA, area) && areaCode.containsKey(Integer.valueOf(area));
    }

    private static boolean isDate6(String date) {
        return isDate8("19" + date);
    }

    private static boolean isDate8(String date) {
        if (!RegexUtil.test(REGEX_DATE8, date)) {
            return false;
        }
        int[] iaMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
        return !(year < MIN_YEAR || year > MAX_YEAR) && !(month < 1 || month > 12) && !(day < 1 || day > iaMonthDays[month - 1]);
    }

}
