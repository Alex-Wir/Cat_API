package com.thecatapi.downloader.unit.service;

import com.thecatapi.downloader.dto.CatRequestDto;
import com.thecatapi.downloader.repository.DataProvider;
import com.thecatapi.downloader.service.RequestBuilder;
import com.thecatapi.downloader.service.impl.CatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatServiceImplTest {

    @InjectMocks
    private CatServiceImpl catService;

    @Mock
    private DataProvider dataProvider;

    @Mock
    private RequestBuilder requestBuilder;

    @Test
    public void getAllTest() {
        final String request = new String();
        final Set<String> stringSet = Collections.singleton(new String());
        when(requestBuilder.getRequest(any(CatRequestDto.class))).thenReturn(request);
        when(dataProvider.getAll(request)).thenReturn(stringSet);
        assertEquals(catService.getAll(any(CatRequestDto.class)), stringSet);
    }

}
