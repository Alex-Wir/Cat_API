package com.thecatapi.downloader.unit.dto.validator;

import com.thecatapi.downloader.dto.constraint.MimeTypeConstraint;
import com.thecatapi.downloader.dto.constraint.validator.MimeTypeValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MimeTypeValidatorTest {

    @InjectMocks
    private MimeTypeValidator mimeTypeValidator;

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    MimeTypeConstraint mimeTypeConstraint;

    @ParameterizedTest
    @MethodSource("provideArguments")
    public void isValidTest(Set<String> set, boolean expectedResult) {
        assertEquals(mimeTypeValidator.isValid(set, context), expectedResult);
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of(Collections.singleton("jpg"), true),
                Arguments.of(Collections.singleton("png"), true),
                Arguments.of(Collections.singleton("gif"), true),
                Arguments.of(Collections.singleton("XXX"), false),
                Arguments.of(Stream.of("jpg", "png").collect(Collectors.toSet()), true),
                Arguments.of(Stream.of("gif", "png").collect(Collectors.toSet()), true),
                Arguments.of(Stream.of("jpg", "png", "gif").collect(Collectors.toSet()), true),
                Arguments.of(Stream.of("jpg", "png", "gif", "XXX").collect(Collectors.toSet()), false)
        );
    }

    @Test
    public void initializeTest() {
        assertDoesNotThrow(() -> mimeTypeValidator.initialize(mimeTypeConstraint));
    }
}
