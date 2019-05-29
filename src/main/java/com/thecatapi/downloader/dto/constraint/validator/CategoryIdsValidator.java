package com.thecatapi.downloader.dto.constraint.validator;

import com.thecatapi.downloader.dto.constraint.CategoryIdsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryIdsValidator implements ConstraintValidator<CategoryIdsConstraint, Set<String>> {

    private Set<String> validCategoryIds;

    private CategoryIdsValidator() {
        validCategoryIds = Stream.of("1", "2", "3", "4", "5", "6", "7", "9", "10", "14", "15").collect(Collectors.toSet());
    }

    @Override
    public void initialize(CategoryIdsConstraint categoryIds) {
    }

    @Override
    public boolean isValid(Set<String> categoryIds, ConstraintValidatorContext context) {
        if (categoryIds == null) {
            return true;
        }
        for (String categoryId : categoryIds) {
            if (!validCategoryIds.contains(categoryId)) {
                return false;
            }
        }
        return true;
    }
}

