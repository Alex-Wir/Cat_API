package com.thecatapi.downloader.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:download.properties")
public class LocalStorage {

    private final LocalizedMessageSource localizedMessageSource;

    @Value("${download.folder}")
    private String folder;

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;

    public String saveFileByUrl(String urlString) {

        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
        System.out.println(fileName);

        if (fileName.isEmpty()) {
            log.error("Invalid file name in URL = " + urlString);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        URL url;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            log.error("Invalid URL = " + urlString, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        File file = new File(folder + fileName);

        try {
            FileUtils.copyURLToFile(url, file, CONNECTION_TIMEOUT, READ_TIMEOUT);
        } catch (IOException e) {
            log.error("Error during saving file " + fileName, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        return folder + fileName;
    }
}