package com.milmile.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Movie [id=%d] is not found";

    public MovieNotFoundException(Integer id) {
        super(MESSAGE.formatted(id));
    }

}
