package com.vkobilarz.rpgbot.processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Character {
    private UUID id;
    private User user;
    private CharacterStats baseStats;
    private CharacterStats currentStats;
    private CharacterState state;
}
