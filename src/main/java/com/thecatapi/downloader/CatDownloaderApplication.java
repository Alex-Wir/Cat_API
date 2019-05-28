package com.thecatapi.downloader;

import com.thecatapi.downloader.config.MessagesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MessagesConfiguration.class)
@SpringBootApplication
public class CatDownloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatDownloaderApplication.class, args);
    }
}
