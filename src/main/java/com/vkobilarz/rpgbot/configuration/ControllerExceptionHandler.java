package com.vkobilarz.rpgbot.configuration;

import com.vkobilarz.rpgbot.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleBusinessValidationException(ValidationException ex) {
        List<ErrorResponse> errors = ex.getValidationResult()
                .getReasons()
                .stream()
                .map(error -> new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error.toString()))
                .toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    static class ErrorResponse {
        private final int code;
        private final String message;

        public ErrorResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
