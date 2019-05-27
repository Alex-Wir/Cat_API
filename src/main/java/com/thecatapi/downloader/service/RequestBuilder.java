package com.thecatapi.downloader.service;

import com.thecatapi.downloader.model.CatRequest;
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

        System.out.println("RequestBuilder Start " + catRequest);

        if (catRequest.isEmpty()) {
            return "";
        }

        String size = catRequest.getSize();
        Set<String> mimeTypes = catRequest.getMimeTypes();
        String order = catRequest.getOrder();
        byte limit = catRequest.getLimit();
        long page = catRequest.getPage();
        Set<String> categoryIds = catRequest.getCategoryIds();
        //String format = catRequest.getFormat();
        String breedId = catRequest.getBreedId();

        StringBuilder request = new StringBuilder();
        request.append(QUESTION_MARK);

        if (size != null) {
            request.append(SIZE);
            request.append(size);
            request.append(AMPERSAND);
        }

        if (mimeTypes != null) {
            request.append(MIME_TYPES);
            request.append(String.join(",", mimeTypes));
            request.append(AMPERSAND);
        }

        if (order != null) {
            request.append(ORDER);
            request.append(order);
            request.append(AMPERSAND);
        }

        if (limit != 0) {
            request.append(LIMIT);
            request.append(limit);
            request.append(AMPERSAND);
        }

        if (page != 0) {
            request.append(PAGE);
            request.append(page);
            request.append(AMPERSAND);
        }

        if (categoryIds != null) {
            request.append(CATEGORY_IDS);
            request.append(String.join(",", categoryIds));
            request.append(AMPERSAND);
        }

        if (breedId != null) {
            request.append(BREED_ID);
            request.append(breedId);
            request.append(AMPERSAND);
        }

        System.out.println("RequestBuilder end " + request);

        return request.toString();
    }
}
