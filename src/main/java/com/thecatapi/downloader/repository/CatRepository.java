package com.thecatapi.downloader.repository;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CatRepository {

    private static final String SEARCH_URL = "https://api.thecatapi.com/v1/images/search";
    private static final String UTF8 = "UTF-8";

    /**
     * Prevent javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
     */
    public CatRepository() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null, null, null);
            SSLContext.setDefault(ctx);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO add LOG & RuntimeExcept
        }
    }

    public String getOneRandom() {
        JSONArray jsonArray = getJsonArray(SEARCH_URL);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String urlString = jsonObject.getString("url");
        System.out.println(urlString);
        return saveOne(urlString);
    }

    public Set<String> getAllByLimit(long limit) {
        JSONArray jsonArray = getJsonArray(SEARCH_URL + "?limit=" + limit);
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
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray(IOUtils.toString(new URL(urlForSearch), Charset.forName(UTF8)));
        } catch (IOException e) {
            System.out.println("Error reading JSON by url");
            //TODO add LOG & RuntimeExcept
            e.printStackTrace();
        }
        return jsonArray;
    }

    private String saveOne(String urlString) {

        String fileName = "";
        final Matcher matcher = Pattern.compile("images\\/").matcher(urlString);
        if (matcher.find()) {
            fileName = urlString.substring(matcher.end()).trim();
        }
        File newFile = new File("cats/" + fileName);
        URL url = null;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL!!!");
            //TODO add LOG & RuntimeExcept
            e.printStackTrace();
        }

        try {
            FileUtils.copyURLToFile(url, newFile, 10000, 10000);
        } catch (IOException e) {
            System.out.println("Save file " + fileName + " error!!!");
            //TODO add LOG & RuntimeExcept
            e.printStackTrace();
        }

        if (newFile.exists()) {
            String filePath = "cats/" + fileName.toString();
            return filePath;
        } else {
            System.out.println("Saved file  " + fileName + " not found!!!");
            //TODO add LOG & RuntimeExcept
            return "";
        }
    }
}
