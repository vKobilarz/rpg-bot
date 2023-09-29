package com.vkobilarz.rpgbot.processor.services;

import com.vkobilarz.rpgbot.core.models.Character;
import com.vkobilarz.rpgbot.core.models.Combat;
import com.vkobilarz.rpgbot.processor.models.ActionName;
import com.vkobilarz.rpgbot.processor.repositories.CharacterRepository;
import com.vkobilarz.rpgbot.processor.repositories.CombatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatService {
    private final CombatRepository combatRepository;
    private final CharacterRepository characterRepository;
    public CombatService(CombatRepository combatRepository, CharacterRepository characterRepository) {
        this.combatRepository = combatRepository;
        this.characterRepository = characterRepository;
    }

    public void start(Character attackingCharacter, Character defendingCharacter) {
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
    public void finish(Combat combat) {
        combat.getAttackingCharacter().getState().setInCombat(false);
        combat.getDefendingCharacter().getState().setInCombat(false);

        combat.setFinished(true);

        combatRepository.save(combat);
    }
    public Combat getActiveCombatByPlayer(Character player, boolean isAttackingTurn) {
        return combatRepository.findActiveCombatByPlayer(player.getId(), isAttackingTurn);
    }
}
