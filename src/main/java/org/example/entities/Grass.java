package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;

public class Grass extends Entity {
    private final String sprite = "\uD83C\uDF31";

    public Grass(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.RESOURCE;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }


    /**
     * Simulates the grass being consumed by a herbivore.
     *
     * @param gameMap   the game map containing entities
     * @param herbivore the herbivore consuming the grass
     */

    public void takeDamage(GameMap gameMap, Herbivore herbivore) {
//        Coordinates tempHerbivoresCoordinates = new Coordinates(herbivore);
//        herbivore.setCoordinates(this.coordinates);
//        entities.put(this.coordinates, herbivore);
//        entities.remove(tempHerbivoresCoordinates);

        Coordinates oldCoordinates = herbivore.getCoordinates();
        Coordinates newCoordinates = this.getCoordinates();

        // Перемещение травоядного
        herbivore.setCoordinates(newCoordinates);

        // Обновление карты
        gameMap.getEntities().put(newCoordinates, herbivore);
        gameMap.getEntities().remove(oldCoordinates);
    }
}
