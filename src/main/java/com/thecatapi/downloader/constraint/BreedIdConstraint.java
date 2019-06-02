package com.thecatapi.downloader.constraint;

import com.thecatapi.downloader.constraint.validator.BreedIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BreedIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BreedIdConstraint {
    String message() default "{request.breedId.allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}