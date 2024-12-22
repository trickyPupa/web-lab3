package web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorY implements ConstraintValidator<ValidationX, Double>  {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value <= 5.0 && value >= -5.0;
    }
}
