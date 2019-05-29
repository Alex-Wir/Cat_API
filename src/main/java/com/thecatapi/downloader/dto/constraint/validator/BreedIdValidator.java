package com.thecatapi.downloader.dto.constraint.validator;

import com.thecatapi.downloader.dto.constraint.BreedIdConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreedIdValidator implements ConstraintValidator<BreedIdConstraint, String> {

    private Set<String> validBreedIds;

    /**
     * Breed Ids from https://api.thecatapi.com/v1/breeds
     */
    private BreedIdValidator() {
        validBreedIds = Stream.of(
                "abys", "aege", "abob", "acur", "asho", "awir", "amau", "amis", "bali", "bamb", "beng",
                "birm", "bomb", "bslo", "bsho", "bure", "buri", "cspa", "ctif", "char", "chau", "chee",
                "csho", "crex", "cymr", "cypr", "drex", "dons", "lihu", "emau", "ebur", "esho", "hbro",
                "hima", "jbob", "java", "khao", "kora", "kuri", "lape", "mcoo", "mala", "manx", "munc",
                "nebe", "norw", "ocic", "orie", "pers", "pixi", "raga", "ragd", "rblu", "sava", "sfol",
                "srex", "siam", "sibe", "sing", "snow", "soma", "sphy", "tonk", "toyg", "tang", "tvan",
                "ycho"
        ).collect(Collectors.toSet());
    }

    @Override
    public void initialize(BreedIdConstraint breedId) {
    }

    @Override
    public boolean isValid(String breedId, ConstraintValidatorContext context) {
        if (breedId == null) {
            return true;
        }
        return (validBreedIds.contains(breedId.toLowerCase()));
    }
}


