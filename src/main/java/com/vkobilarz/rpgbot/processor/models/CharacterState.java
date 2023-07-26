package com.vkobilarz.rpgbot.processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CharacterState {
    private Character inCombatEnemy;
    private Boolean inCombat;
}
