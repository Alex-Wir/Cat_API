package com.thecatapi.downloader.dto.constraint.impl;

import com.thecatapi.downloader.dto.constraint.MimeTypeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class MimeTypeConstraintImpl implements
        ConstraintValidator<MimeTypeConstraint, Set<String>> {

    private static final String JPG = "jpg";
    private static final String PNG = "png";
    private static final String GIF = "gif";

    @Override
    public void initialize(MimeTypeConstraint mimeTypes) {
    }

    @Override
    public boolean isValid(Set<String> mimeTypes, ConstraintValidatorContext context) {
        if (mimeTypes==null) {
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