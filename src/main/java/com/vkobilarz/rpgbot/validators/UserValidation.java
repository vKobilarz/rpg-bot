package com.vkobilarz.rpgbot.validators;

import com.vkobilarz.rpgbot.exceptions.ValidationException;
import com.vkobilarz.rpgbot.models.User;

import java.util.function.Function;

public interface UserValidation extends Function<User, ValidationResult> {
    static UserValidation validateThat() {
        return user -> ValidationResult.valid();
    }

    default UserValidation isNameValid() {
        return user -> NameValidation.validateThat()
                .isRequired()
                .isFullName()
                .apply(user.getName());
    }

    default UserValidation isUrlValid() {
        return user -> UrlValidation.validateThat()
                .isRequired()
                .hasUrlPattern()
                .apply(user.getImageUrl());
    }

    default UserValidation isEmailValid() {
        return user -> EmailValidation.validateThat()
                .isRequired()
                .hasEmailPattern()
                .apply(user.getEmail());
    }

    default UserValidation isPhoneNumberValid() {
        return user -> PhoneNumberValidation.validateThat()
                .isRequired()
                .hasPhoneNumberPattern()
                .apply(user.getPhoneNumber());
    }
}
