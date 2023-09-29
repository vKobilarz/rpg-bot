package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BreatherAction implements Action{
    @Override
    public boolean validate(Character character) {
        return character.getState().getInCombat();
    }

    @Override
    public void executePreAction(Character character) {

    }

    @Override
    public void executeAction(Character character) {
        character.getCurrentStats().setStamina(character.getBaseStats().getStamina());
    }

    @Override
    public void executePosAction(Character character) {

    }
}
