package com.thecatapi.downloader.service;

import com.thecatapi.downloader.dto.CatRequest;

import java.util.Set;

public interface CatService {

    String getOneRandom();

    Set<String> getAll(CatRequest catRequest);
}
