package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
