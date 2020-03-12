package io.github.msidolphin.easyvalidator.annotation;

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import io.github.msidolphin.easyvalidator.validator.ConstraintValidator;

public class SexValidator extends ConstraintValidator<String> {

    @Override
    public void validate(String gender, BaseConstraint constraint) {
        if (!"male".equals(gender) && !"female".equals(gender)) {
            throw new RuntimeException(constraint.getMessage());
        }
    }

}
