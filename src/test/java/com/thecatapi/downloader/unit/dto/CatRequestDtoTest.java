package com.thecatapi.downloader.unit.dto;

import com.thecatapi.downloader.dto.CatRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatRequestDtoTest {

    private CatRequestDto catRequest;

    @BeforeEach
    public void initialize(){
        CatRequestDto catRequest = new CatRequestDto();
    }

    @Test
    public void isEmptyTest() {
        assertTrue(catRequest.isEmpty());
    }

    @Test
    public void isEmptyTestWhenCatRequestIsNotEmpty(){
        catRequest.setPage(1L);
        assertFalse(catRequest.isEmpty());
    }
}
