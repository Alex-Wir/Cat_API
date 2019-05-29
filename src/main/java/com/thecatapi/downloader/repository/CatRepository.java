package com.thecatapi.downloader.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PropertySource("classpath:download.properties")
@Component
@Slf4j
public class CatRepository {

    //TODO add Except for zero image result

    private static final String SEARCH_URL = "https://api.thecatapi.com/v1/images/search";
    private static final String UTF8 = "UTF-8";

    private final LocalizedMessageSource localizedMessageSource;

    @Value("${download.folder}")
    private String folder;

    /**
     * Prevent javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
     */
    public CatRepository(LocalizedMessageSource localizedMessageSource) {
        this.localizedMessageSource = localizedMessageSource;
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null, null, null);
            SSLContext.setDefault(ctx);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("Error during initialize SSLContext", e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }
    }

    public Set<String> getAll(String request) {
        JSONArray jsonArray = getJsonArray(SEARCH_URL + request);
        //TODO delete SOUT
        System.out.println(SEARCH_URL + request);
        Set<String> filePaths = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String urlString = jsonObject.getString("url");
            System.out.println(urlString);
            filePaths.add(saveOne(urlString));
        }
        return filePaths;
    }

    private JSONArray getJsonArray(String urlForSearch) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(IOUtils.toString(new URL(urlForSearch), Charset.forName(UTF8)));
        } catch (IOException e) {
            log.error("Error reading JSON by URL = "+urlForSearch, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }
        return jsonArray;
    }

    private String saveOne(String urlString) {

        String fileName = "";
        final Matcher matcher = Pattern.compile("images\\/").matcher(urlString);
        if (matcher.find()) {
            fileName = urlString.substring(matcher.end()).trim();
        }
        File newFile = new File(folder + fileName);

        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            log.error("Invalid URL = "+urlString, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }

        try {
            FileUtils.copyURLToFile(url, newFile, 10000, 10000);
        } catch (IOException e) {
            log.error("Error during saving file "+fileName, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));

        }

        return (folder + fileName);
    }
}
