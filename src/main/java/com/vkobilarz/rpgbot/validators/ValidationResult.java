package com.vkobilarz.rpgbot.validators;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public interface ValidationResult {

    static ValidationResult valid() {
        return ValidationSupport.valid();
    }

    static ValidationResult invalid(String reason) {
        return new Invalid(List.of(reason));
    }

    static ValidationResult invalid(List<String> reasons) {
        return new Invalid(reasons);
    }

    boolean isValid();

    List<String> getReasons();

    default <X extends Throwable> void ifInvalidThrow(Function<ValidationResult, X> consumer) throws X {
        if (!isValid()) {
            throw consumer.apply(this);
        }
    }

    final class ValidationSupport {
        private static final ValidationResult valid = new ValidationResult() {
            @Override
            public boolean isValid() {
                return true;
            }

            @Override
            public List<String> getReasons() {
                return Collections.emptyList();
            }
        };

        static ValidationResult valid() {
            return valid;
        }
    }

    final class Invalid implements ValidationResult {
        private final List<String> reasons;

        public Invalid(List<String> reasons) {
            this.reasons = reasons;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public List<String> getReasons() {
            return reasons;
        }
    }
}
