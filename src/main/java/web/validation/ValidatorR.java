package web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorR implements ConstraintValidator<ValidationR, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= 1 && value <= 5;
    }
}