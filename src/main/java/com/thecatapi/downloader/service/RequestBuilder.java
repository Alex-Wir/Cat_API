package com.thecatapi.downloader.service;

import com.thecatapi.downloader.dto.CatRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RequestBuilder {

    private static final char AMPERSAND = '&';
    private static final char QUESTION_MARK = '?';

    private static final String SIZE = "size=";
    private static final String MIME_TYPES = "mime_types=";
    private static final String ORDER = "order=";
    private static final String LIMIT = "limit=";
    private static final String PAGE = "page=";
    private static final String CATEGORY_IDS = "category_ids=";
    private static final String BREED_ID = "breed_id=";

    public String getRequest(CatRequest catRequest) {

        if (catRequest.isEmpty()) {
            return "";
        }

        String size = catRequest.getSize();
        Set<String> mimeTypes = catRequest.getMimeTypes();
        String order = catRequest.getOrder();
        byte limit = catRequest.getLimit();
        long page = catRequest.getPage();
        Set<String> categoryIds = catRequest.getCategoryIds();
        String breedId = catRequest.getBreedId();

        StringBuilder request = new StringBuilder();
        request.append(QUESTION_MARK);

        if (size != null) {
            request.append(SIZE).append(size).append(AMPERSAND);
        }

        if (mimeTypes != null) {
            request.append(MIME_TYPES).append(String.join(",", mimeTypes)).append(AMPERSAND);
        }

        if (order != null) {
            request.append(ORDER).append(order).append(AMPERSAND);
        }

        if (limit != 0) {
            request.append(LIMIT).append(limit).append(AMPERSAND);
        }

        if (page != 0) {
            request.append(PAGE).append(page).append(AMPERSAND);
        }

        if (categoryIds != null) {
            request.append(CATEGORY_IDS).append(String.join(",", categoryIds)).append(AMPERSAND);
        }

        if (breedId != null) {
            request.append(BREED_ID).append(breedId).append(AMPERSAND);
        }

        request.deleteCharAt(request.lastIndexOf(String.valueOf(AMPERSAND)));
        return request.toString();
    }
}
