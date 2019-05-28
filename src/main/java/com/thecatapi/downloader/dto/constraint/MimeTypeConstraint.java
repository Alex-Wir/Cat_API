package com.thecatapi.downloader.dto.constraint;

import com.thecatapi.downloader.dto.constraint.impl.MimeTypeConstraintImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MimeTypeConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MimeTypeConstraint {
    String message() default "{request.mimeTypes.allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}