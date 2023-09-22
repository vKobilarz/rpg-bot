package com.vkobilarz.rpgbot.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "character_stats", schema = "public")
public class CharacterStats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_stats_generator")
    @SequenceGenerator(name = "character_stats_generator", sequenceName = "character_stats_id", allocationSize = 1)
    private int id;
    @JsonProperty("health")
    private float health;
    @JsonProperty("damage")
    private float damage;
}
