package com.thecatapi.downloader.service;

import com.thecatapi.downloader.dto.CatRequestDto;

import java.util.Set;

/**
 * Service interface for CatRequestDto
 */
public interface CatService {

    Set<String> getAll(CatRequestDto catRequest);
}
