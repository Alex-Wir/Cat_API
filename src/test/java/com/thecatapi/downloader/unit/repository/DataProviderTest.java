package com.thecatapi.downloader.unit.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import com.thecatapi.downloader.repository.DataProvider;
import com.thecatapi.downloader.repository.LocalStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataProviderTest {

    @InjectMocks
    private DataProvider dataProvider;

    @Mock
    private LocalStorage localStorage;
    private LocalizedMessageSource localizedMessageSource;

    @Test
    public void testGetAll() {
        String filePath = "newFilePath";
        Set<String> filePaths = Collections.singleton(filePath);
        when(localStorage.saveFileByUrl(any(String.class))).thenReturn(filePath);
        assertEquals(dataProvider.getAll(""), filePaths);
    }

    @Test
    public void testGetAll_limitEqual2_shouldReturnSetWith2Elements() {
        String limitRequest = "?limit=2";
        when(localStorage.saveFileByUrl(any(String.class)))
                .thenReturn("newString1")
                .thenReturn("newString2");
        assertEquals(dataProvider.getAll(limitRequest).size(), 2);
    }

    @Test
    public void testGetAll_wrongRequest_shouldThrowException() {
        String wrongRequest = "djsakfjsakdf";
        assertThrows(RuntimeException.class, ()->dataProvider.getAll(wrongRequest));
    }

    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new DataProvider(localizedMessageSource, localStorage));
    }
}