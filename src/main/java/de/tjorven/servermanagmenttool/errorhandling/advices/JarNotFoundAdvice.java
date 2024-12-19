package de.tjorven.servermanagmenttool.errorhandling.advices;

import de.tjorven.servermanagmenttool.errorhandling.exceptions.JarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JarNotFoundAdvice {

    @ExceptionHandler(JarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String jarNotFoundHandler(JarNotFoundException ex) {
        return ex.getMessage();
    }
}
