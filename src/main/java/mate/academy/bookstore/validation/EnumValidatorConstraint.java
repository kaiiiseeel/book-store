package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import mate.academy.bookstore.entity.Order.Status;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, Status> {
    private Status[] status;

    @Override
    public void initialize(EnumValidator constraint) {
        this.status = constraint.anyOf();
    }

    @Override
    public boolean isValid(Status value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(status).contains(value);
    }
}
