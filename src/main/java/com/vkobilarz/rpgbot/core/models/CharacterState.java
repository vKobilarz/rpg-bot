package com.vkobilarz.rpgbot.core.models;

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
