package com.thecatapi.downloader.service.impl;

import com.thecatapi.downloader.repository.CatRepository;
import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public String getOneRandom() {
        return catRepository.getOneRandom();
    }
}
