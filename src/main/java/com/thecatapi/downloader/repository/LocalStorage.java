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
import java.net.URL;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:download.properties")
public class LocalStorage {

    private final LocalizedMessageSource localizedMessageSource;

    @Value("${download.folder}")
    private String folder;

    @Value("${connection.timeout}")
    private int connectionTimeout;

    @Value("${read.timeout}")
    private int readTimeout;

    public String saveFileByUrl(String urlString) {

        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);

        if (fileName.isEmpty()) {
            log.error("Invalid file name in URL = " + urlString);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        try {
            URL url = new URL(urlString);
            File file = new File(folder + fileName);
            FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
        } catch (IOException e) {
            log.error("Error during saving file by URL = " + urlString, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        log.info("File successfully saved " + fileName);

        return folder + fileName;
    }
}