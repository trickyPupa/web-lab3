package web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ValidatorR implements ConstraintValidator<ValidationR, Double> {
    private final Set<Double> validRValues = Set.of(1d, 1.5, 2d, 2.5, 3d, 3.5, 4d);

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && validRValues.contains(value);
    }
}