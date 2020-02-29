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

package io.github.msidolphin.easyvalidator.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class ValidateCache {

    private static ValidateCache ourInstance = new ValidateCache();

    public static ValidateCache getInstance() {
        return ourInstance;
    }

    private Map<Class, List<Field>> classFieldSetMap;
    private Map<Field, Annotation[]> fieldAnnotationMap;

    private ValidateCache() {
        classFieldSetMap = new HashMap<>();
        fieldAnnotationMap = new HashMap<>();
    }

    public void setClassFields(Class classType, List<Field> fieldSet) {
        if (null == fieldSet) fieldSet = new ArrayList<>();
        classFieldSetMap.put(classType, fieldSet);
    }

    public void setFieldAnnotations(Field field, Annotation[] annotations) {
        if (null == annotations) annotations = new Annotation[0];
        fieldAnnotationMap.put(field, annotations);
    }

    public List<Field> getFieldsByClass(Class classType) {
        return classFieldSetMap.get(classType);
    }

    public Annotation[] getAnnotationsByField(Field field) {
        return fieldAnnotationMap.get(field);
    }

}
