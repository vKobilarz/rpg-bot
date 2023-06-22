package com.vkobilarz.rpgbot.validators;

import java.util.function.Function;

public interface NameValidation extends Function<String, ValidationResult> {
    static NameValidation validateThat() {
        return name -> ValidationResult.valid();
    }

    default NameValidation isRequired() {
        return name -> {
            if (name == null || name.isEmpty() || name.isBlank()) {
                return ValidationResult.invalid("Name is mandatory");
            }

            return ValidationResult.valid();
        };
    }

    default NameValidation isFullName() {
        return name -> {
            if (!name.contains(" ")) {
                return ValidationResult.invalid("Name must be a full name");
            }

            return ValidationResult.valid();
        };
    }
}
