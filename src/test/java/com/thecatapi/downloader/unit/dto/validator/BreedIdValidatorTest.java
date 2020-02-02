package com.thecatapi.downloader.unit.dto.validator;

import com.thecatapi.downloader.dto.constraint.BreedIdConstraint;
import com.thecatapi.downloader.dto.constraint.validator.BreedIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BreedIdValidatorTest {

    @InjectMocks
    private BreedIdValidator breedIdValidator;
    private ConstraintValidatorContext context;
    private BreedIdConstraint breedIdConstraint;

    @Test
    public void testInitialize() {
        assertDoesNotThrow(() -> breedIdValidator.initialize(breedIdConstraint));
    }

    @Test
    public void testIsValid_BreedIdIsNull_shouldReturnTrue(){
        assertTrue(breedIdValidator.isValid(null, context));
    }

    /**
     * Breed Ids from https://api.thecatapi.com/v1/breeds
     */
    @ParameterizedTest
    @ValueSource(strings = {"abys", "java", "norw", "siam"})
    public void testIsValid_validBreedId_shouldReturnTrue(String validBreedId) {
        assertTrue(breedIdValidator.isValid(validBreedId, context));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "XXXX", "asdaldsjf", "1234"})
    public void testIsValid_invalidBreedId_shouldReturnFalse(String invalidBreedId) {
        assertFalse(breedIdValidator.isValid(invalidBreedId, context));
    }
}