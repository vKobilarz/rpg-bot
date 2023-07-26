package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.processor.models.Character;

public interface BaseAction {
    public Boolean canExecuteAction(Character character);

    public void execute(Character character);
}
