package com.vkobilarz.rpgbot.exceptions;

import com.vkobilarz.rpgbot.validators.ValidationResult;

public class ValidationException extends RuntimeException {
    private final ValidationResult validationResult;

    public ValidationException(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
