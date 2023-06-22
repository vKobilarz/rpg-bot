package com.vkobilarz.rpgbot.validators;

import java.util.function.Function;

public interface UrlValidation extends Function<String, ValidationResult> {
    static UrlValidation validateThat() {
        return url -> ValidationResult.valid();
    }

    default UrlValidation isRequired() {
        return url -> {
            if (url == null || url.isEmpty() || url.isBlank()) {
                return ValidationResult.invalid("Url is mandatory");
            }

            return ValidationResult.valid();
        };
    }

    default UrlValidation hasUrlPattern() {
        return url -> {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return ValidationResult.valid();
            }

            return ValidationResult.invalid("Url must follow an url pattern");
        };
    }
}
