package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.Combat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CombatRepository extends JpaRepository<Combat, Integer> {
    Combat findByAttackingCharacter_IdAndAttackingCharacter_TypeAndIsAttackingTurnAndIsFinished(int id, String type, boolean isAttackingTurn, boolean isFinished);}
