package arimayuji.eletiva.api.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import arimayuji.eletiva.api.dtos.ErrorResponse;
import arimayuji.eletiva.application.exceptions.MusicNotFoundException;
import arimayuji.eletiva.domain.exceptions.InvalidReviewValueRangeException;
import arimayuji.eletiva.domain.exceptions.MusicAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MusicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMusicNotFoundException(MusicNotFoundException ex) {
        var error = new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Música não encontrada.",
                ex.getMessage(),
                null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MusicAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(MusicAlreadyExistsException ex) {
        var error = new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Música já existe.",
                ex.getMessage(),
                null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidReviewValueRangeException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(InvalidReviewValueRangeException ex) {
        var error = new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Valor de avaliação inválido.",
                ex.getMessage(),
                null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
