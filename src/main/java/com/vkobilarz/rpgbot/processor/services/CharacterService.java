package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.processor.models.Character;
import com.vkobilarz.rpgbot.processor.models.CharacterState;
import com.vkobilarz.rpgbot.processor.models.CharacterStats;
import com.vkobilarz.rpgbot.processor.models.User;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import com.vkobilarz.rpgbot.processor.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
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
        Number health = formatNumber(50 + Math.random() * 25);
        Number damage = formatNumber(10 + Math.random() * 5);

        CharacterStats baseStats = CharacterStats.builder().health(health).damage(damage).build();
        CharacterStats currentStats = CharacterStats.builder().health(health).damage(damage).build();
        return Character.builder()
                .id(UUID.randomUUID())
                .baseStats(baseStats)
                .currentStats(currentStats)
                .build();
    }

    private Number formatNumber(Number number) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        try {
            return numberFormat.parse(numberFormat.format(number));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Character createMockedCharacter() {
        CharacterStats baseStats = CharacterStats.builder().health(100).damage(30).build();
        CharacterStats currentStats = CharacterStats.builder().health(100).damage(30).build();
        CharacterState state = CharacterState.builder().inCombat(false).build();
        User user = userRepository.findUserById(UUID.fromString("08f49f35-2405-49b5-8c12-1c8dc767394d"));

        return Character.builder()
                .id(UUID.fromString("9676645e-1278-48eb-b49c-9485c78662fe"))
                .baseStats(baseStats)
                .currentStats(currentStats)
                .state(state)
                .user(user)
                .build();
    }
}
