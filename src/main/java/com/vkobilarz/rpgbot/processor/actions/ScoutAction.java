package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.processor.services.CharacterService;
import com.vkobilarz.rpgbot.processor.services.CombatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoutAction implements Action {
    private final CharacterService characterService;
    private final CombatService combatService;

    @Override
    public boolean validate(Character character) {
        return ! (boolean) character.getState().getInCombat();
    }
    @Override
    public void executePreAction(Character character) {}

    @Override
    public void executeAction(Character character) {
        Character enemy = characterService.createEnemy();

        combatService.start(character, enemy);
    }
    @Override
    public void executePosAction(Character character) {}
}
