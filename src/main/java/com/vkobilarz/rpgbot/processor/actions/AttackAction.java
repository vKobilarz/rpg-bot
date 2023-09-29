package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;
import com.vkobilarz.rpgbot.processor.services.CombatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AttackAction implements Action {
    private final CombatService combatService;

    @Override
    public boolean validate(Character character) {
        return character.getState().getInCombat();
    }

    @Override
    public void executePreAction(Character character) {}
    @Override
    public void executeAction(Character player) {
        Combat combat = combatService.getActiveCombatByPlayer(player, true);
        Character target = combat.getDefendingCharacter();

        player.executeAttack(target);
        combat.setAttackingTurn(false);
    }
    @Override
    public void executePosAction(Character player) {
        Combat combat = combatService.getActiveCombatByPlayer(player, false);

        if (combat.shouldFinish()) {
            combatService.finish(combat);
            return;
        }

        Character enemy = combat.getDefendingCharacter();
        enemy.executeAttack(player);
        combat.setAttackingTurn(true);

        if (combat.shouldFinish()) {
            combatService.finish(combat);
            player.resetHealth();
        }
    }
}
