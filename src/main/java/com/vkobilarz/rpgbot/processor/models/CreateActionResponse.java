package com.vkobilarz.rpgbot.processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CreateActionResponse {
    private Action requestedAction;
    private Character character;
}
