package com.thecatapi.downloader.constraint.validator;

import com.thecatapi.downloader.constraint.CategoryIdsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for categoryIds validation
 */
public class CategoryIdsValidator implements ConstraintValidator<CategoryIdsConstraint, Set<String>> {

    private Set<String> validCategoryIds;

    /**
     * Category Ids from https://docs.thecatapi.com/api-reference/categories/categories-list
     */
    private CategoryIdsValidator() {
        validCategoryIds = Stream.of("1", "2", "3", "4", "5", "6", "7", "9", "10", "14", "15").collect(Collectors.toSet());
    }

    /**
     * Implement method initialize of interface ConstraintValidator
     *
     * @param categoryIds - BreedIdConstraint
     */
    @Override
    public void initialize(CategoryIdsConstraint categoryIds) {
    }

    /**
     * Validate categoryIds
     *
     * @param categoryIds - categoryIds from request
     * @param context     - ConstraintValidatorContext
     * @return - true, if categoryIds is valid
     */
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

