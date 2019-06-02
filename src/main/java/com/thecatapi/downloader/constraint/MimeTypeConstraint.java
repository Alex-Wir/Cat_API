package com.thecatapi.downloader.constraint;

import com.thecatapi.downloader.constraint.validator.MimeTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MimeTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MimeTypeConstraint {
    String message() default "{request.mimeTypes.allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}