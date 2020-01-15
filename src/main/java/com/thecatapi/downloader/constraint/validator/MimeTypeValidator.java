package com.thecatapi.downloader.constraint.validator;

import com.thecatapi.downloader.constraint.MimeTypeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * Class for mimeTypes validation
 */
public class MimeTypeValidator implements
        ConstraintValidator<MimeTypeConstraint, Set<String>> {

    private static final String JPG = "jpg";
    private static final String PNG = "png";
    private static final String GIF = "gif";

    /**
     * Implement method initialize of interface ConstraintValidator
     *
     * @param mimeType - MimeTypeConstraint
     */
    @Override
    public void initialize(MimeTypeConstraint mimeType) {
    }

    /**
     * Validate mimeTypes
     *
     * @param mimeTypes - mimeTypes from request
     * @param context   - ConstraintValidatorContext
     * @return - true, if mimeTypes is valid
     */
    @Override
    public boolean isValid(Set<String> mimeTypes, ConstraintValidatorContext context) {
        if (mimeTypes == null) {
            return true;
        }
        for (String mimeType : mimeTypes) {
            mimeType = mimeType.toLowerCase();
            if (!(mimeType.equals(JPG) || mimeType.equals(PNG) || mimeType.equals(GIF))) {
                return false;
            }
        }
        return true;
    }
}