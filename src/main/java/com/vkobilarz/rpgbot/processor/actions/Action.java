package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;

public interface Action {
    boolean validate(Character character);
    void run(Character character);

    public default void execute(Character character) {
        if (!validate(character)) {
            throw new RuntimeException("Can not execute action in current state");
        }

        run(character);
    }
}
