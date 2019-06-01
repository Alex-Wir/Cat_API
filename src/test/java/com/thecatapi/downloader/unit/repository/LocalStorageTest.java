package com.thecatapi.downloader.unit.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import com.thecatapi.downloader.repository.LocalStorage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LocalStorageTest {

    @InjectMocks
    private LocalStorage localStorage;

    private LocalizedMessageSource localizedMessageSource;

    private final static String downloadFolderForTest = "images_test/";

    @BeforeEach
    private void setTestDownloadFolder() {
        try {
            Field field = this.localStorage.getClass().getDeclaredField("folder");
            field.setAccessible(true);
            field.set(this.localStorage, downloadFolderForTest);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveFileByUrl_jpgFile() {
        String urlJpgFile = "https://cdn2.thecatapi.com/images/e3s.jpg";
        assertTrue(localStorage.saveFileByUrl(urlJpgFile).endsWith("jpg"));
    }

    @Test
    public void testSaveFileByUrl_wrongFileNameInUrl_shouldThrowException() {
        String wrongUrl = "google.com/";
        assertThrows(RuntimeException.class, () -> localStorage.saveFileByUrl(wrongUrl));
    }

    @Test
    public void testSaveFileByUrl_wrongUrl_shouldThrowException() {
        String wrongUrl = "djaflskjdfl";
        assertThrows(RuntimeException.class, () -> localStorage.saveFileByUrl(wrongUrl));
    }

    @AfterAll
    private static void deleteTestDownloadFolder() {
        try {
            FileUtils.deleteDirectory(new File(downloadFolderForTest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
