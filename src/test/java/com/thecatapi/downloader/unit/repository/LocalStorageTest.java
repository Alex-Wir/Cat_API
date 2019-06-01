package com.thecatapi.downloader.unit.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import com.thecatapi.downloader.repository.LocalStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LocalStorageTest {

    @InjectMocks
    private LocalStorage localStorage;

    private LocalizedMessageSource localizedMessageSource;

    @Test
    public void testSaveFileByUrl_jpgFile() {
        String urlJpgFile = "https://cdn2.thecatapi.com/images/e3s.jpg";
        setTestFolderForDownload(localStorage);
        assertTrue(localStorage.saveFileByUrl(urlJpgFile).endsWith("jpg"));
    }

    @Test
    public void testSaveFileByUrl_wrongFileNameInUrl_shouldThrowException() {
        String wrongUrl = "google.com/";
        assertThrows(RuntimeException.class, ()->localStorage.saveFileByUrl(wrongUrl));
    }

    @Test
    public void testSaveFileByUrl_wrongUrl_shouldThrowException() {
        String wrongUrl = "djaflskjdfl";
        assertThrows(RuntimeException.class, ()->localStorage.saveFileByUrl(wrongUrl));
    }

    private void setTestFolderForDownload(LocalStorage localStorage) {
        try {
            Field field = localStorage.getClass().getDeclaredField("folder");
            field.setAccessible(true);
            field.set(localStorage, "images_test/");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
