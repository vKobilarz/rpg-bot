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
@Getter
@Setter
@Builder
@Entity
@Table(name = "character", schema = "public")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_generator")
    @SequenceGenerator(name = "character_generator", sequenceName = "character_id", allocationSize = 1)
    private int id;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "base_stats_id")
    private CharacterStats baseStats;
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "current_stats_id")
    private CharacterStats currentStats;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "state_id")
    private CharacterState state;
    private String type;
    @JsonIgnore
    public float getPlayerHealth() {
        return currentStats.getHealth();
    }
    @JsonIgnore
    public boolean isDead() {
        return currentStats.getHealth() <= 0;
    }
    @JsonIgnore
    public void resetHealth() {
        float maxHealth = baseStats.getHealth();

        currentStats.setHealth(maxHealth);
    }
    public void executeAttack(Character target) {
        float damage = currentStats.getDamage();
        float health = target.getPlayerHealth();

        float healthWithDamageTaken = health - damage;

        target.getCurrentStats().setHealth(healthWithDamageTaken);
    }
}
