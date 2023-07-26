package com.vkobilarz.rpgbot.processor.actions;

import com.vkobilarz.rpgbot.processor.models.Character;
import com.vkobilarz.rpgbot.processor.models.CharacterState;
import com.vkobilarz.rpgbot.processor.models.CharacterStats;
import org.springframework.stereotype.Component;

@Component
public class AttackAction implements BaseAction {
    @Override
    public Boolean canExecuteAction(Character character) {
        Boolean isCharacterInCombat = character.getState().getInCombat();

        return isCharacterInCombat;
    }

    @Override
    public void execute(Character character) {
        executePlayerTurn(character);

        if (isEnemySlain(character)) {
            removePlayerFromCombat(character);

            return;
        }

        executeEnemyTurn(character);

        if (isPlayerSlain(character)) {
            removePlayerFromCombat(character);
            resetsPlayer(character);
        }
    }

    private void executePlayerTurn(Character character) {
        Number playerDamage = character.getCurrentStats().getDamage();
        Number enemyHealth = getEnemyHealth(character);

        Number enemyHealthWithDamageTaken = enemyHealth.floatValue() - playerDamage.floatValue();

        character.getState().getInCombatEnemy().getCurrentStats().setHealth(enemyHealthWithDamageTaken);
    }

    private void executeEnemyTurn(Character character) {
        Number playerHealth = getPlayerHealth(character);
        Number enemyDamage = character.getState().getInCombatEnemy().getCurrentStats().getDamage();

        Number playerHealthWithDamageTaken = playerHealth.floatValue() - enemyDamage.floatValue();

        character.getCurrentStats().setHealth(playerHealthWithDamageTaken);
    }

    private boolean isEnemySlain(Character character) {
        Number enemyHealth = getEnemyHealth(character);

        return enemyHealth.floatValue() <= 0;
    }

    private boolean isPlayerSlain(Character character) {
        Number playerHealth = getPlayerHealth(character);

        return playerHealth.floatValue() <= 0;
    }

    private void resetsPlayer(Character character) {
        Number playerMaxHealth = character.getBaseStats().getHealth();

        character.getCurrentStats().setHealth(playerMaxHealth);
    }

    private void removePlayerFromCombat(Character character) {
        CharacterState newState = CharacterState.builder()
                .inCombat(false)
                .build();

        character.setState(newState);
    }

    private Number getEnemyHealth(Character character) {
        return character.getState().getInCombatEnemy().getCurrentStats().getHealth();
    }
    private Number getPlayerHealth(Character character) {
        return character.getCurrentStats().getHealth();
    }
}
