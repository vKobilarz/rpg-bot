package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.processor.models.Character;
import com.vkobilarz.rpgbot.processor.models.CharacterState;
import com.vkobilarz.rpgbot.processor.services.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoutAction implements BaseAction {
    private final CharacterService characterService;

    @Override
    public Boolean canExecuteAction(Character character) {
        Boolean isCharacterInCombat = character.getState().getInCombat();

        return !isCharacterInCombat;
    }

    @Override
    public void execute(Character character) {
        Character enemy = characterService.createEnemy();

        CharacterState newState = CharacterState.builder()
                .inCombatEnemy(enemy)
                .inCombat(true)
                .build();

        character.setState(newState);
    }
}
