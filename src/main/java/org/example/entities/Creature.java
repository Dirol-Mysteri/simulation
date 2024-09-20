package org.example.entities;

import org.example.Coordinates;
import org.example.enums.CreatureType;
import org.example.enums.EntityType;

public abstract class Creature extends Entity {
    CreatureType typeOfCreature;
    int speed;
    int hp;

    public Creature(int positionN, int positionM, EntityType entityType) {
        this.speed = 1;
        this.hp = 1;
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = entityType;
    }

    abstract void makeMove();
}
