package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.CharacterState;
import com.vkobilarz.rpgbot.core.models.CharacterStats;
import com.vkobilarz.rpgbot.core.models.User;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import com.vkobilarz.rpgbot.processor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;

    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;

        characterRepository.create(createMockedCharacter());
    }

    public Character createEnemy() {
        Random random = new Random();
        float health = 50 + random.nextFloat() * 25;
        float damage = 10 + random.nextFloat() * 5;

        CharacterStats baseStats = CharacterStats.builder().health(health).damage(damage).build();
        CharacterStats currentStats = CharacterStats.builder().health(health).damage(damage).build();
        return Character.builder()
                .id(UUID.randomUUID())
                .baseStats(baseStats)
                .currentStats(currentStats)
                .build();
    }

    private Character createMockedCharacter() {
        CharacterStats baseStats = CharacterStats.builder().health(100).damage(30).build();
        CharacterStats currentStats = CharacterStats.builder().health(100).damage(30).build();
        CharacterState state = CharacterState.builder().inCombat(false).build();
        User user = userRepository.getUserById(999);

        return Character.builder()
                .id(UUID.fromString("9676645e-1278-48eb-b49c-9485c78662fe"))
                .baseStats(baseStats)
                .currentStats(currentStats)
                .state(state)
                .user(user)
                .build();
    }
}
