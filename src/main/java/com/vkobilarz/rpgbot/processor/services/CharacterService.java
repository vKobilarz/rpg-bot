package com.vkobilarz.rpgbot.processor.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.CharacterState;
import com.vkobilarz.rpgbot.core.models.CharacterStats;
import com.vkobilarz.rpgbot.core.models.User;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@AllArgsConstructor
@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public Character createEnemy() {
        Random random = new Random();
        float health = 50 + (int) (random.nextFloat() * 25);
        float damage = 10 + (int) (random.nextFloat() * 5);

        CharacterStats baseStats = CharacterStats.builder().health(health).damage(damage).build();
        CharacterStats currentStats = CharacterStats.builder().health(health).damage(damage).build();

        Character enemy = Character.builder()
                .baseStats(baseStats)
                .currentStats(currentStats)
                .state(CharacterState.builder().inCombat(true).build())
                .user(User.builder().id(0).build())
                .type("ENEMY")
                .build();

        characterRepository.save(enemy);

        return enemy;
    }

}
