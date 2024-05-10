package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,
        Object> {
    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstField = constraintAnnotation.firstFieldName();
        secondField = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object value,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstField);
        Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondField);

        return Objects.equals(firstObject, secondObject);
    }
}
