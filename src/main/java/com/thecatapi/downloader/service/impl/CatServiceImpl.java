package com.thecatapi.downloader.service.impl;

import com.thecatapi.downloader.dto.CatRequest;
import com.thecatapi.downloader.repository.CatRepository;
import com.thecatapi.downloader.service.CatService;
import com.thecatapi.downloader.service.RequestBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final RequestBuilder requestBuilder;

    @Override
    public String getOneRandom() {
        return catRepository.getOneRandom();
    }

    @Override
    public Set<String> getAll(CatRequest catRequest) {
        String request = requestBuilder.getRequest(catRequest);
        return catRepository.getAll(request);
    }
}
