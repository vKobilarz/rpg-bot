package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.Character;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CharacterRepository {
    private final ArrayList<Character> characters;

    public Character findCharacterById(UUID id) {
        return characters.stream().filter(character -> character.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void create(Character character) {
        characters.add(character);
    }
}
