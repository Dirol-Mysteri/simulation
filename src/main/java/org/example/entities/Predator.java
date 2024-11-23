package org.example.entities;

import org.example.GameMap;
import org.example.enums.EntityType;

import java.util.List;
import java.util.Random;

public class Predator extends Creature {

    private static final int DAMAGE = 2;
    private static final int SPEED = 1;
    private static final int HP = 10;

    public Predator(int positionN, int positionM) {
        super(positionN, positionM, EntityType.PREDATOR);
        List<String> predatorSprites = List.of("\uD83D\uDC0A", "\uD83E\uDD81", "\uD83D\uDC3A");
        this.sprite = predatorSprites.get(new Random().nextInt(predatorSprites.size()));
        this.speed = SPEED;
        this.hp = HP;
    }

    @Override
    public void interactWithTarget(Entity target, GameMap gameMap) {
        if (target.isType(EntityType.HERBIVORE)) {
            ((Herbivore) target).takeDamage(gameMap, this);
        }
    }

    public int getDamage() {
        return DAMAGE;
    }
}
