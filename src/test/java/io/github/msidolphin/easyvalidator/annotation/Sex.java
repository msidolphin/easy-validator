package io.github.msidolphin.easyvalidator.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = SexValidator.class)
public @interface Sex {
}
