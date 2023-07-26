package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.processor.models.Character;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CharacterRepository {
    private final ArrayList<Character> characters;

    public Character findCharacterById(UUID id) {
        return characters.stream().filter(character -> character.getId().toString().equals(id.toString()))
                .findFirst()
                .orElse(null);
    }

    public void create(Character character) {
        characters.add(character);
    }
}
