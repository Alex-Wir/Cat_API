package com.thecatapi.downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Response DTO class including error status and message
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private HttpStatus httpStatus;
    private String message;

}
