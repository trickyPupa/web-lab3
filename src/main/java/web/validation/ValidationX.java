package web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorX.class})
public @interface ValidationX {
    String message() default "Некорректная координата x";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}