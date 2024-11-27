package web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorR.class})
public @interface ValidationR {
    String message() default "Некорректная координата r";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}