package com.vkobilarz.rpgbot.core.models;

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
@Table(name = "character_state", schema = "public")
public class CharacterState {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_state_generator")
    @SequenceGenerator(name = "character_state_generator", sequenceName = "character_state_id", allocationSize = 1)
    private int id;
    private Boolean inCombat;
}
