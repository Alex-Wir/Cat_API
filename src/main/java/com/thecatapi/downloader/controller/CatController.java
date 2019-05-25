package com.thecatapi.downloader.controller;

import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cats")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOne() {
        return catService.getOneRandom();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Set<String> getAllWithParam(@RequestParam long limit) {
        System.out.println(limit);
        return catService.getAllByLimit(limit);
    }

}
