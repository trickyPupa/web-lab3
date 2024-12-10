package web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@DecimalMax(value = "5.0", message = "y coordinate must be less than 5.0")
@DecimalMin(value = "-5.0", message = "y coordinate must be greater than -5.0")
public @interface ValidationY {
    String message() default "Incorrect y coordinate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}