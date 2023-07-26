package com.vkobilarz.rpgbot.processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Action {
    private UUID characterId;
    private String type;
}
