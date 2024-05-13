package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

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
        try {
            Object firstObject = getFieldValue(value, firstField);
            Object secondObject = getFieldValue(value, secondField);
            return Objects.equals(firstObject, secondObject);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }

    }

    private Object getFieldValue(Object value, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = value.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(value);
    }
}
