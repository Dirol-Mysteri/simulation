package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

import java.util.HashMap;

public class Grass extends Entity {
    private String sprite = "\uD83C\uDF31";

    public Grass(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.RESOURCE;
    }

    public String getSprite() {
        return this.sprite;
    }

    public void takeDamage(HashMap<Coordinates, Entity> entities, Herbivore herbivore) {
        Coordinates tempHerbivoresCoordinates = new Coordinates(herbivore.getCoordinates().getN(), herbivore.getCoordinates().getM());
        herbivore.setCoordinates(this.coordinates);
        entities.put(this.coordinates, herbivore);
        entities.put(tempHerbivoresCoordinates, new FreeSpace(tempHerbivoresCoordinates.getN(), tempHerbivoresCoordinates.getM()));

    }
}
