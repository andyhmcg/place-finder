package com.amcg.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { NotEmptyStringValidator.class })
public @interface NotEmptyString {

    String message() default "Value is empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
