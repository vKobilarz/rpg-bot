package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.CharacterState;
import org.springframework.stereotype.Component;

@Component
public class AttackAction implements Action {
    @Override
    public boolean validate(Character character) {
        return character.getState().getInCombat();
    }

    @Override
    public void run(Character player) {
        Character enemy = player.getState().getInCombatEnemy();

        executeTurn(player, enemy);
        if (isSlain(enemy)) {
            removePlayerFromCombat(player);
            return;
        }

        executeTurn(enemy, player);
        if (isSlain(player)) {
            removePlayerFromCombat(player);
            resetsPlayer(player);
        }
    }

    private void executeTurn(Character source, Character target) {
        float damage = source.getCurrentStats().getDamage();
        float health = target.getPlayerHealth();

        float healthWithDamageTaken = health - damage;

        target.getCurrentStats().setHealth(healthWithDamageTaken);
    }

    private boolean isSlain(Character character) {
        float health = character.getPlayerHealth();

        return health <= 0;
    }

    private void resetsPlayer(Character character) {
        float playerMaxHealth = character.getBaseStats().getHealth();

        character.getCurrentStats().setHealth(playerMaxHealth);
    }

    private void removePlayerFromCombat(Character character) {
        CharacterState newState = CharacterState.builder()
                .inCombat(false)
                .build();

        character.setState(newState);
    }


}
