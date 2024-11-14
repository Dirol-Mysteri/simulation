package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Herbivore extends Creature {
    private static final int SPEED = 1;
    private static final int HP = 10;

    public Herbivore(int positionN, int positionM) {
        super(positionN, positionM, EntityType.HERBIVORE);
        ArrayList<String> herbivoreSprites = new ArrayList<>(List.of("\uD83D\uDC37", "\uD83D\uDC14", "\uD83E\uDD86"));
        this.sprite = herbivoreSprites.get((int) Math.round(Math.random() * 2));
        this.speed = SPEED;
        this.hp = HP;
    }

    @Override
    public void interactWithTarget(Entity target, GameMap gameMap) {
        var entities = gameMap.getEntities();
        if (target.isType(EntityType.RESOURCE)) {
            ((Grass) target).takeDamage(entities, this);
        }
    }

    public void takeDamage(GameMap gameMap, Predator predator) {
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        int damage = predator.getDamage();
        if (this.hp > damage) {
            this.hp = this.hp - damage;
        } else {
            // If Herbivore dies, it's place takes Free Cell
            Coordinates tempPredatorCoordinates = new Coordinates(predator);
            predator.setCoordinates(this.coordinates);
            entities.put(this.coordinates, predator);
            entities.remove(tempPredatorCoordinates);
            int newHerbivoresQuantity = gameMap.getHerbivoresQuantity() - 1;
            gameMap.setHerbivoresQuantity(newHerbivoresQuantity);
        }
    }
}
