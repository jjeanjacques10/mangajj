package com.mangajj.mangacontrol.adapter.web.advice;

import com.mangajj.mangacontrol.application.exception.ChapterNotFoundException;
import com.mangajj.mangacontrol.application.exception.NotFoundMangaException;
import com.mangajj.mangacontrol.application.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDetailsDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Invalid Fields Error")
                .details(ex.getMessage())
                .developerMethod(ex.getClass().getName())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionDetailsDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMethod(ex.getClass().getName())
                .build());
    }

    @ExceptionHandler(value = {ChapterNotFoundException.class, NotFoundMangaException.class, UserNotFoundException.class})
    public ResponseEntity<ExceptionDetailsDTO> handleNotFound(NotFoundMangaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionDetailsDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not Found")
                .details(ex.getMessage())
                .developerMethod(ex.getClass().getName())
                .build());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDetailsDTO> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExceptionDetailsDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.FORBIDDEN.value())
                .title("User not authorized")
                .details(ex.getMessage())
                .developerMethod(ex.getClass().getName())
                .build());
    }

}
