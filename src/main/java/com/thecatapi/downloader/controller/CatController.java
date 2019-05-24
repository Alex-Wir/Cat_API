package com.thecatapi.downloader.controller;

import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CatController {

    private final CatService catService;
	
    @RequestMapping("/")
    public String getOne() {
        return catService.getOneRandom();
    }

}
