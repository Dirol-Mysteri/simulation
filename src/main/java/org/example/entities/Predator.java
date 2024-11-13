package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Predator extends Creature {

    private final int DAMAGE = 2;

    public Predator(int positionN, int positionM) {
        super(positionN, positionM, EntityType.PREDATOR);
        ArrayList<String> predatorSprites = new ArrayList<>(List.of("\uD83D\uDC0A", "\uD83E\uDD81", "\uD83D\uDC3A"));
        this.sprite = predatorSprites.get((int) Math.round(Math.random() * 2));
        this.speed = 1;
        this.hp = 10;
    }

    @Override
    public void interactWithTarget(Entity target, HashMap<Coordinates, Entity> entities) {
        if (target.isType(EntityType.HERBIVORE)) {
            ((Herbivore) target).takeDamage(entities, this);
            // НАДО ПОДУМАТЬ, КАК ТУТ ПРАВИЛЬНО СДЕЛАТЬ CASTING
        }
    }

    public int getDamage() {
        return DAMAGE;
    }
}
