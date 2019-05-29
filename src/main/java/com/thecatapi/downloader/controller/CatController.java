package com.thecatapi.downloader.controller;

import com.thecatapi.downloader.dto.CatRequestDto;
import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/cats")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<Set<String>> getAll(@Valid @RequestBody CatRequestDto catRequest) {
        System.out.println("Controller " + catRequest);
        return new ResponseEntity<>(catService.getAll(catRequest), HttpStatus.OK);
    }

}
