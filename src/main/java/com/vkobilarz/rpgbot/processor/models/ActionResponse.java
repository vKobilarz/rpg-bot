package com.vkobilarz.rpgbot.processor.models;

import com.vkobilarz.rpgbot.core.models.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ActionResponse {
    private ActionRequest action;
    private Character character;
}
