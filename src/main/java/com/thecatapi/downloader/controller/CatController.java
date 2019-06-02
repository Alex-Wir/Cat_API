package com.thecatapi.downloader.controller;

import com.thecatapi.downloader.dto.CatRequestDto;
import com.thecatapi.downloader.service.CatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

/**
 * Cat controller
 */
@RestController
@RequestMapping("/cats")
@AllArgsConstructor
@Slf4j
public class CatController {

    private final CatService catService;

    /**
     * Download files by request
     *
     * @param catRequest - request with search parameters
     * @return - ResponseEntity with Set of downloaded files paths
     */
    @PostMapping
    public ResponseEntity<Set<String>> getAll(@Valid @RequestBody CatRequestDto catRequest) {
        log.info("Start request " + catRequest);
        return new ResponseEntity<>(catService.getAll(catRequest), HttpStatus.OK);
    }
}
