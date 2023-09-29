package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.Combat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CombatRepository extends JpaRepository<Combat, Integer> {
    // TODO: Remove this example
    @Query("select c from Combat c where c.attackingCharacter.id = ?1")
    Combat findByAttackingCharacter_Id(int id);

    // TODO: Add @Query and rename function
    Combat findByAttackingCharacter_IdAndAttackingCharacter_TypeAndIsAttackingTurnAndIsFinished(int id, String type, boolean isAttackingTurn, boolean isFinished);}
