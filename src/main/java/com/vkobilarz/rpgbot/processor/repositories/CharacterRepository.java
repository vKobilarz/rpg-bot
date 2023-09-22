package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {}
