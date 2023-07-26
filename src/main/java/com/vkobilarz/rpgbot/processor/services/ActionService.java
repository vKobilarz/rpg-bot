package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.processor.actions.AttackAction;
import com.vkobilarz.rpgbot.processor.actions.BaseAction;
import com.vkobilarz.rpgbot.processor.actions.ScoutAction;
import com.vkobilarz.rpgbot.processor.models.Action;
import com.vkobilarz.rpgbot.processor.models.Character;
import com.vkobilarz.rpgbot.processor.models.CreateActionResponse;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActionService {
    private final CharacterRepository characterRepository;
    private final ScoutAction scoutAction;
    private final AttackAction attackAction;

    public CreateActionResponse executeAction(Action action) throws Exception {
        Character character = characterRepository.findCharacterById(action.getCharacterId());
        BaseAction actionExecutor = getActionExecutor(action.getType());

        if (actionExecutor == null) {
            throw new Exception();
        }

        if (!actionExecutor.canExecuteAction(character)) {
            throw new Exception();
        }

        actionExecutor.execute(character);

        return CreateActionResponse.builder()
                .requestedAction(action)
                .character(character)
                .build();
    }

    private BaseAction getActionExecutor(String actionType) {
        if (actionType == null) {
            return null;
        }

        switch (actionType) {
            case "SCOUT": return scoutAction;
            case "ATTACK": return attackAction;
        }

        return null;
    }
}
