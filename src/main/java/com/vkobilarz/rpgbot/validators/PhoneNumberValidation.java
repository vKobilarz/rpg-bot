package com.vkobilarz.rpgbot.validators;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface PhoneNumberValidation extends Function<String, ValidationResult> {
    static PhoneNumberValidation validateThat() {
        return phoneNUmber -> ValidationResult.valid();
    }

    default PhoneNumberValidation isRequired() {
        return phoneNumber -> {
            if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank()) {
                return ValidationResult.invalid("PhoneNumber is mandatory");
            }

            return ValidationResult.valid();
        };
    }

    default PhoneNumberValidation hasPhoneNumberPattern() {
        return email -> {
            Pattern pattern = Pattern.compile(
                    "^(\\(\\d{1,2}\\))[ 9]?\\d{5}-?\\d{4}$");

            Matcher matcher = pattern.matcher(email);

            if (!matcher.matches()) {
                return ValidationResult.invalid("PhoneNumber must follow a phone number pattern");
            }

            return ValidationResult.valid();
        };
    }
}
