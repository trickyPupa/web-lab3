package web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ValidatorX implements ConstraintValidator<ValidationX, Double> {
    private final Set<Double> validXValues = Set.of(-5d, -4d, -3d, -2d, -1d, 0d, 1d, 2d, 3d);

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
//        return value != null && validXValues.contains(value);
        return value != null && value <= 3.0 && value >= -5.0;
    }
}