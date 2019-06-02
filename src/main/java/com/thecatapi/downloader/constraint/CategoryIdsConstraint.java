package com.thecatapi.downloader.constraint;

import com.thecatapi.downloader.constraint.validator.CategoryIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation for validation categoryIds
 */
@Documented
@Constraint(validatedBy = CategoryIdsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryIdsConstraint {
    String message() default "{request.categoryIds.allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}