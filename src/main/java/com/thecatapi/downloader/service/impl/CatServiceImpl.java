package com.thecatapi.downloader.service.impl;

import com.thecatapi.downloader.repository.CatRepository;
import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public String getOneRandom() {
        return catRepository.getOneRandom();
    }

    @Override
    public Set<String> getAllByLimit(long limit) {
        return catRepository.getAllByLimit(limit);
    }
}
