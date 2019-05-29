package com.thecatapi.downloader.service;

import com.thecatapi.downloader.dto.CatRequestDto;

import java.util.Set;

public interface CatService {

    Set<String> getAll(CatRequestDto catRequest);
}
