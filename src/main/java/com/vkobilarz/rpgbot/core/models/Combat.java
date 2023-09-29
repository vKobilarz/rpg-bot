package com.vkobilarz.rpgbot.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "combat", schema = "public")
public class Combat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "combat_generator")
    @SequenceGenerator(name = "combat_generator", sequenceName = "combat_id", allocationSize = 1)
    private int id;
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "attacking_character_id")
    private Character attackingCharacter;
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "defending_character_id")
    private Character defendingCharacter;
    private boolean isAttackingTurn;
    private boolean isFinished;
    @JsonIgnore
    public boolean shouldFinish() {
        return attackingCharacter.isDead() || defendingCharacter.isDead();
    }
}
