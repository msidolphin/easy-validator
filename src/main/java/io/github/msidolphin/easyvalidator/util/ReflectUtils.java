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

import io.github.msidolphin.easyvalidator.annotation.Constraint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class ReflectUtils {

    public static List<Field> getFieldsByClass(Class cls) {
        List<Field> fieldSet = new ArrayList<>();
        for (Class<?> clazz = cls; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            if (CommonUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                if (!field.getName().equals("class") && !field.getName().equals("serialVersionUID")) {
                    fieldSet.add(field);
                }
            }
        }
        return fieldSet;
    }

    public static Constraint getConstraintAnnotation (Annotation annotation) {
        if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
           Annotation[] annotations = annotation.annotationType().getAnnotations();
           for (int i = 0; i < annotations.length; ++i) {
               if (annotations[i] instanceof Constraint) {
                   return (Constraint) annotations[i];
               }
           }
        }
        return null;
    }

}
