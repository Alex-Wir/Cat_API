package com.thecatapi.downloader.unit.dto.validator;

import com.thecatapi.downloader.dto.constraint.MimeTypeConstraint;
import com.thecatapi.downloader.dto.constraint.validator.MimeTypeValidator;
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
public class MimeTypeValidatorTest {

    @InjectMocks
    private MimeTypeValidator mimeTypeValidator;
    private ConstraintValidatorContext context;
    private MimeTypeConstraint mimeTypeConstraint;

    @Test
    public void initializeTest() {
        assertDoesNotThrow(() -> mimeTypeValidator.initialize(mimeTypeConstraint));
    }

    @Test
    public void isValidTest_MimeTypeIsNull_shouldReturnTrue(){
        assertTrue(mimeTypeValidator.isValid(null, context));
    }

    @ParameterizedTest
    @MethodSource("provideValidArguments")
    public void isValidTest_setValidMimeTypes_shouldReturnTrue(Set<String> validMimeTypes) {
        assertTrue(mimeTypeValidator.isValid(validMimeTypes, context));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    public void isValidTest_setInvalidMimeTypes_shouldReturnFalse(Set<String> invalidMimeTypes) {
        assertFalse(mimeTypeValidator.isValid(invalidMimeTypes, context));
    }

    /**
     * Mime types from https://docs.thecatapi.com/example-by-type
     *
     * @return - Stream of valid mime-types
     */
    private static Stream<Arguments> provideValidArguments() {
        return Stream.of(
                Arguments.of(new HashSet<>()),
                Arguments.of(Collections.singleton("jpg")),
                Arguments.of(Collections.singleton("png")),
                Arguments.of(Collections.singleton("gif")),
                Arguments.of(Stream.of("jpg", "png").collect(Collectors.toSet())),
                Arguments.of(Stream.of("gif", "png").collect(Collectors.toSet())),
                Arguments.of(Stream.of("jpg", "png", "gif").collect(Collectors.toSet()))
        );
    }

    private static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
                Arguments.of(Collections.singleton(" ")),
                Arguments.of(Collections.singleton("XXX")),
                Arguments.of(Stream.of("jpg", "png", "gif", "XXX").collect(Collectors.toSet()))
        );
    }
}