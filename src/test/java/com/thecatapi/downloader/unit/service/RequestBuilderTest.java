package com.thecatapi.downloader.unit.service;

import com.thecatapi.downloader.dto.CatRequestDto;
import com.thecatapi.downloader.service.RequestBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestBuilderTest {

    private RequestBuilder requestBuilder = new RequestBuilder();

    @ParameterizedTest
    @MethodSource("provideArguments")
    public void getRequestTest(CatRequestDto catRequest, String request) {
        assertEquals(requestBuilder.getRequest(catRequest), request);
    }

    private static Stream<Arguments> provideArguments() {

        CatRequestDto catRequestWithPage = new CatRequestDto();
        catRequestWithPage.setPage(1L);
        CatRequestDto catRequestWithLimit = new CatRequestDto();
        catRequestWithLimit.setLimit((byte) 1);
        CatRequestDto catRequestWithSize = new CatRequestDto();
        catRequestWithSize.setSize("full");
        CatRequestDto catRequestWithOneType = new CatRequestDto();
        catRequestWithOneType.setMimeTypes(Collections.singleton("jpg"));
        CatRequestDto catRequestWithAllTypes = new CatRequestDto();
        catRequestWithAllTypes.setMimeTypes(Stream.of("jpg", "gif", "png").collect(Collectors.toSet()));
        CatRequestDto catRequestWithOrder = new CatRequestDto();
        catRequestWithOrder.setOrder("asc");
        CatRequestDto catRequestWithOneCategory = new CatRequestDto();
        catRequestWithOneCategory.setCategoryIds(Collections.singleton("1"));
        CatRequestDto catRequestWithManyCategories = new CatRequestDto();
        catRequestWithManyCategories.setCategoryIds(Stream.of("1", "2", "3").collect(Collectors.toSet()));
        CatRequestDto catRequestWithBreedId = new CatRequestDto();
        catRequestWithBreedId.setBreedId("java");
        CatRequestDto catRequestWithManyParameters = new CatRequestDto();
        catRequestWithManyParameters.setLimit((byte)1);
        catRequestWithManyParameters.setPage(1L);

        return Stream.of(
                Arguments.of(new CatRequestDto(), ""),
                Arguments.of(catRequestWithPage, "?page=1"),
                Arguments.of(catRequestWithLimit, "?limit=1"),
                Arguments.of(catRequestWithSize, "?size=full"),
                Arguments.of(catRequestWithOneType, "?mime_types=jpg"),
                Arguments.of(catRequestWithAllTypes, "?mime_types=jpg,gif,png"),
                Arguments.of(catRequestWithOrder, "?order=asc"),
                Arguments.of(catRequestWithOneCategory, "?category_ids=1"),
                Arguments.of(catRequestWithManyCategories, "?category_ids=1,2,3"),
                Arguments.of(catRequestWithBreedId, "?breed_id=java"),
                Arguments.of(catRequestWithManyParameters, "?limit=1&page=1")
        );
    }
}