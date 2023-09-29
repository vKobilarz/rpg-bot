package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;

public interface Action {
    boolean validate(Character character);
    void executePreAction(Character character);
    void executeAction(Character character);
    void executePosAction(Character character);
    public default void process(Character character) {
        if (!validate(character)) {
            throw new RuntimeException("Can not execute action in current state");
        }
        executePreAction(character);
        executeAction(character);
        executePosAction(character);
    }
}
