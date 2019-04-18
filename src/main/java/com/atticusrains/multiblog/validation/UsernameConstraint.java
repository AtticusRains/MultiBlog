package com.atticusrains.multiblog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "Username must consist of letters and numbers only.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
