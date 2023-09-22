package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AttackAction implements Action {
    @Override
    public boolean validate(Character character) {
        return character.getState().getInCombat();
    }

    @Override
    public void run(Character character) {
    }

    @Override
    public void run(Combat combat) {
        boolean isAttackingTurn = combat.isAttackingTurn();
        Character source = isAttackingTurn ? combat.getAttackingCharacter() : combat.getDefendingCharacter();
        Character target = isAttackingTurn ? combat.getDefendingCharacter() : combat.getAttackingCharacter();

        float damage = source.getCurrentStats().getDamage();
        float health = target.getPlayerHealth();

        float healthWithDamageTaken = health - damage;

        target.getCurrentStats().setHealth(healthWithDamageTaken);
        combat.setAttackingTurn(!isAttackingTurn);
    }
}
