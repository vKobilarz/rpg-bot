package com.vkobilarz.rpgbot.validators;

import java.util.UUID;
import java.util.function.Function;

public interface IdValidation extends Function<String, ValidationResult> {

    static IdValidation validateThat() {
        return id -> ValidationResult.valid();
    }

    default IdValidation isNumericId() {
        return id -> {
            try {
                Long numericId = Long.getLong(id);
            } catch (NumberFormatException e) {
                return ValidationResult.invalid("Id is not a numeric value");
            }

            return ValidationResult.valid();
        };
    }

    default IdValidation isRequired() {
        return id -> {
            if (id == null || id.isEmpty() || id.isBlank()) {
                return ValidationResult.invalid("ID is mandatory");
            }

            return ValidationResult.valid();
        };
    }

    default IdValidation isUuid() {
        return id -> {
            try {
                UUID uuid = UUID.fromString(id);
            } catch (Exception e) {
                return ValidationResult.invalid("Id is not on UUID format");
            }

            return ValidationResult.valid();
        };
    }

}
