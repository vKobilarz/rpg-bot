package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;

public interface Action {
    boolean validate(Character character);
    void run(Character character);
    void run(Combat combat);

    public default void process(Character character) {
        if (!validate(character)) {
            throw new RuntimeException("Can not execute action in current state");
        }

        run(character);
    }
    public default void process(Combat combat) {
        if (!validate(combat.getAttackingCharacter())) {
            throw new RuntimeException("Can not execute action in current state");
        }

        run(combat);
    }
}
