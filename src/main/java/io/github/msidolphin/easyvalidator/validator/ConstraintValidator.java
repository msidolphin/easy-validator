package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;

public class ConstraintValidator<T> implements IValidator<BaseConstraint> {

    @Override
    public final boolean validate(Object value, BaseConstraint constraint, String message) {
        return true;
    }

    public void validate(T value, BaseConstraint constraint) {}

}
