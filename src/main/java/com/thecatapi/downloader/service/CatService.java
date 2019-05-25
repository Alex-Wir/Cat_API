package com.thecatapi.downloader.service;

import java.util.Set;

public interface CatService {

    String getOneRandom();

    Set<String> getAllByLimit(long limit);
}
