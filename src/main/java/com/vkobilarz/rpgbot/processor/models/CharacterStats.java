package com.vkobilarz.rpgbot.processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CharacterStats {
    private Number health;
    private Number damage;
}
