package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Herbivore extends Creature {
    private static final int SPEED = 1;
    private static final int HP = 10;

    public Herbivore(int positionN, int positionM) {
        super(positionN, positionM, EntityType.HERBIVORE);
        List<String> herbivoreSprites = List.of("\uD83D\uDC37", "\uD83D\uDC14", "\uD83E\uDD86");
        this.sprite = herbivoreSprites.get(new Random().nextInt(herbivoreSprites.size()));
        this.speed = SPEED;
        this.hp = HP;
    }

    @Override
    public void interactWithTarget(Entity target, GameMap gameMap) {
        var entities = gameMap.getEntities();
        if (target.isType(EntityType.RESOURCE)) {
            ((Grass) target).takeDamage(gameMap, this);
        }
    }

    public void takeDamage(GameMap gameMap, Predator predator) {
//        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        int damage = predator.getDamage();
        this.hp -= damage;

        if (this.hp <= 0) {
            handleDeath(gameMap, predator);
        }

//        else {
//            // If Herbivore dies, it's place takes Free Cell
//            Coordinates tempPredatorCoordinates = new Coordinates(predator);
//            predator.setCoordinates(this.coordinates);
//            entities.put(this.coordinates, predator);
//            entities.remove(tempPredatorCoordinates);
//            int newHerbivoresQuantity = gameMap.getHerbivoresQTY() - 1;
//            gameMap.setHerbivoresQTY(newHerbivoresQuantity);
//        }
    }

    private void handleDeath(GameMap gameMap, Predator predator) {
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        Coordinates predatorCoordinates = predator.getCoordinates();
        predator.setCoordinates(this.coordinates);
        entities.put(this.coordinates, predator);
        entities.remove(predatorCoordinates);

        gameMap.decrementHerbivoresCount();
    }

}
