package web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@DecimalMax(value = "5.0", message = "Координата y не должна принимать значения выше 5.0")
@DecimalMin(value = "-5.0", message = "Координата y не должна принимать значения ниже -5.0")
public @interface ValidationY {
    String message() default "Некорректная координата y";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}