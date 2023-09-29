package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;
import com.vkobilarz.rpgbot.processor.actions.Action;
import com.vkobilarz.rpgbot.processor.actions.AttackAction;
import com.vkobilarz.rpgbot.processor.models.ActionName;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import com.vkobilarz.rpgbot.processor.repositories.CombatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatService {
    private final CombatRepository combatRepository;
    private final CharacterRepository characterRepository;
    private final Action action;

    public CombatService(CombatRepository combatRepository, CharacterRepository characterRepository, AttackAction action) {
        this.combatRepository = combatRepository;
        this.characterRepository = characterRepository;
        this.action = action;
    }

    public void startCombat(Character attackingCharacter, Character defendingCharacter) {
        Combat combat = Combat.builder()
                .isAttackingTurn(true)
                .isFinished(false)
                .attackingCharacter(attackingCharacter)
                .defendingCharacter(defendingCharacter)
                .build();

        combatRepository.save(combat);

        attackingCharacter.getState().setInCombat(true);
        defendingCharacter.getState().setInCombat(true);

        characterRepository.saveAll(List.of(attackingCharacter, defendingCharacter));
    }
    public void handlePlayerCombat(Character player, Action action) {
        Combat combat = combatRepository.findByAttackingCharacter_IdAndAttackingCharacter_TypeAndIsAttackingTurnAndIsFinished(player.getId(), player.getType(), true, false);

        action.process(combat);

        executeCombat(combat);
    }
    public void executeCombat(Combat combat) {
        Character attackingCharacter = combat.getAttackingCharacter();
        Character defendingCharacter = combat.getDefendingCharacter();

        if (defendingCharacter.isDead()) {
            finishCombat(combat);
            return;
        }

        action.run(combat);

        if (attackingCharacter.isDead()) {
            finishCombat(combat);
            attackingCharacter.resetHealth();
            return;
        }

        combatRepository.save(combat);
    }

    private void executeTurn(Character source, Character target) {
        float damage = source.getCurrentStats().getDamage();
        float health = target.getPlayerHealth();

        float healthWithDamageTaken = health - damage;

        target.getCurrentStats().setHealth(healthWithDamageTaken);
    }

    private void finishCombat(Combat combat) {
        combat.getAttackingCharacter().getState().setInCombat(false);
        combat.getDefendingCharacter().getState().setInCombat(false);

        combat.setFinished(true);

        combatRepository.save(combat);
    }

    public boolean isCombatAction(ActionName actionName) {
        return actionName == ActionName.ATTACK;
    }
}
