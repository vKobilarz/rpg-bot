package com.vkobilarz.rpgbot.validators;

import java.util.function.Function;

public interface EmailValidation extends Function<String, ValidationResult> {
    static EmailValidation validateThat() {
        return email -> ValidationResult.valid();
    }

    default EmailValidation isRequired() {
        return email -> {
            if (email == null || email.isEmpty() || email.isBlank()) {
                return ValidationResult.invalid("Email is mandatory");
            }

            return ValidationResult.valid();
        };
    }

    default EmailValidation hasEmailPattern() {
        return email -> {
            if (!email.contains("@")) {
                return ValidationResult.invalid("Email must follow an email pattern");
            }

            return ValidationResult.valid();
        };
    }
}
