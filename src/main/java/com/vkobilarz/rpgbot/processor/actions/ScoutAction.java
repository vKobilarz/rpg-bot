package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.CharacterState;
import com.vkobilarz.rpgbot.processor.services.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoutAction implements Action {
    private final CharacterService characterService;
    @Override
    public boolean validate(Character character) {
        return !character.getState().getInCombat();
    }

    @Override
    public void run(Character character) {
        Character enemy = characterService.createEnemy();

        CharacterState newState = CharacterState.builder()
                .inCombatEnemy(enemy)
                .inCombat(true)
                .build();

        character.setState(newState);
    }
}
