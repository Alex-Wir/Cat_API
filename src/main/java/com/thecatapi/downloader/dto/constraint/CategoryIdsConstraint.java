package com.thecatapi.downloader.dto.constraint;

import com.thecatapi.downloader.dto.constraint.validator.CategoryIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryIdsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryIdsConstraint {
    String message() default "{request.categoryIds.allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}