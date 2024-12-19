package de.tjorven.servermanagmenttool.errorhandling.advices;

import de.tjorven.servermanagmenttool.errorhandling.exceptions.CouldNotStartJarException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouldNotStartJarAdvice {
    @ExceptionHandler(CouldNotStartJarException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String couldNotStartJarHandler(CouldNotStartJarException ex) {
        return ex.getMessage();
    }
}
