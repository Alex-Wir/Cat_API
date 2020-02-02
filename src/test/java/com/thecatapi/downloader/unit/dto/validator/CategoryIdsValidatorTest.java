package com.thecatapi.downloader.unit.dto.validator;

import com.thecatapi.downloader.dto.constraint.CategoryIdsConstraint;
import com.thecatapi.downloader.dto.constraint.validator.CategoryIdsValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryIdsValidatorTest {

    @InjectMocks
    private CategoryIdsValidator categoryIdsValidator;
    private CategoryIdsConstraint constraint;
    private ConstraintValidatorContext context;

    @Test
    public void initializeTest() {
        assertDoesNotThrow(() -> categoryIdsValidator.initialize(constraint));
    }

    @Test
    public void testIsValid_categoryIdsIsNull_shouldReturnTrue() {
        assertTrue(categoryIdsValidator.isValid(null, context));
    }

    @ParameterizedTest
    @MethodSource("provideValidArguments")
    public void testIsValid_validCategoryIds_shouldReturnTrue(Set<String> validCategoryIds) {
        assertTrue(categoryIdsValidator.isValid(validCategoryIds, context));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    public void testIsValid_invalidCategoryIds_shouldReturnFalse(Set<String> invalidCategoryIds) {
        assertFalse(categoryIdsValidator.isValid(invalidCategoryIds, context));
    }

    /**
     * Category ids from https://docs.thecatapi.com/api-reference/categories/categories-list
     *
     * @return - Stream of set with valid category ids
     */
    private static Stream<Arguments> provideValidArguments() {
        return Stream.of(
                Arguments.of(new HashSet<>()),
                Arguments.of(Collections.singleton("1")),
                Arguments.of(Collections.singleton("2")),
                Arguments.of(Collections.singleton("3")),
                Arguments.of(Collections.singleton("4")),
                Arguments.of(Collections.singleton("5")),
                Arguments.of(Collections.singleton("6")),
                Arguments.of(Collections.singleton("7")),
                Arguments.of(Collections.singleton("9")),
                Arguments.of(Collections.singleton("10")),
                Arguments.of(Collections.singleton("14")),
                Arguments.of(Collections.singleton("15")),
                Arguments.of(Stream.of("1", "2", "3", "4", "5", "6", "7", "9", "10", "14", "15")
                        .collect(Collectors.toSet()))
        );
    }

    private static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
                Arguments.of(Collections.singleton("-1")),
                Arguments.of(Collections.singleton("0")),
                Arguments.of(Collections.singleton("8")),
                Arguments.of(Collections.singleton("11")),
                Arguments.of(Collections.singleton("12")),
                Arguments.of(Collections.singleton("999999")),
                Arguments.of(Collections.singleton(Integer.toString(Integer.MAX_VALUE))),
                Arguments.of(Collections.singleton(Integer.toString(Integer.MIN_VALUE))),
                Arguments.of(Stream.of("1", "2", "3", "0")
                        .collect(Collectors.toSet()))
        );
    }
}