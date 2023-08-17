package com.vkobilarz.rpgbot.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CharacterStats {
    private float health;
    private float damage;
}
