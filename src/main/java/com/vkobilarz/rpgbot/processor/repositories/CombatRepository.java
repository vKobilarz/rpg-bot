package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.Combat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CombatRepository extends JpaRepository<Combat, Integer> {
    @Query("select c from Combat c where c.attackingCharacter.id = ?1 and c.isAttackingTurn = ?2 and isFinished = false")
    Combat findActiveCombatByPlayer(int id, boolean isAttackingTurn);
}