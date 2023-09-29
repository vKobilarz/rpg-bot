package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.processor.actions.AttackAction;
import com.vkobilarz.rpgbot.processor.actions.Action;
import com.vkobilarz.rpgbot.processor.actions.BreatherAction;
import com.vkobilarz.rpgbot.processor.actions.ScoutAction;
import com.vkobilarz.rpgbot.processor.models.ActionName;
import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.processor.models.ActionRequest;
import com.vkobilarz.rpgbot.processor.models.ActionResponse;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActionService {
    private final CharacterRepository characterRepository;
    private final ScoutAction scoutAction;
    private final AttackAction attackAction;
    private final BreatherAction breatherAction;

    public ActionResponse execute(ActionRequest action) {
        Character character = characterRepository.findById(action.getCharacterId()).orElseThrow();

        Action actionExecutor = getActionExecutor(action.getType());

        if (actionExecutor == null) {
            throw new RuntimeException("Action not found");
        }

        actionExecutor.process(character);

        return ActionResponse.builder()
                .action(action)
                .character(character)
                .build();
    }

    private Action getActionExecutor(ActionName action) {
        switch (action) {
            case SCOUT: return scoutAction;
            case ATTACK: return attackAction;
            case BREATHER: return breatherAction;
            default: return null;
        }
    }
}
