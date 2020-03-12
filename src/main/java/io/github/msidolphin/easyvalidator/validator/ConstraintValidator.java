package io.github.msidolphin.easyvalidator.validator;

import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;

public class ConstraintValidator<T> implements IValidator<T, BaseConstraint> {

    @Override
    public void validate(T value, BaseConstraint constraint) {}

}
