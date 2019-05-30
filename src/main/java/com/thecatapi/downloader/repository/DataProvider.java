package com.thecatapi.downloader.repository;

import com.thecatapi.downloader.config.LocalizedMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

@Repository
@Slf4j
public class DataProvider {

    private static final String SEARCH_URL = "https://api.thecatapi.com/v1/images/search";
    private static final String UTF8 = "UTF-8";

    private final LocalizedMessageSource localizedMessageSource;
    private final LocalStorage localStorage;

    /**
     * Prevent javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
     */
    public DataProvider(LocalizedMessageSource localizedMessageSource, LocalStorage localStorage) {
        this.localizedMessageSource = localizedMessageSource;
        this.localStorage = localStorage;
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
        Set<String> filePaths = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String urlString = jsonObject.getString("url");
            filePaths.add(localStorage.saveFileByUrl(urlString));
        }
        return filePaths;
    }

    private JSONArray getJsonArray(String urlForSearch) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(IOUtils.toString(new URL(urlForSearch), Charset.forName(UTF8)));
        } catch (IOException e) {
            log.error("Error reading JSON by URL = " + urlForSearch, e);
            throw new RuntimeException(localizedMessageSource.getMessage("repository.download.error", new Object[]{}));
        }
        return jsonArray;
    }
}
