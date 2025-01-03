package org.example.entities.factories;

import org.example.Coordinates;
import org.example.entities.EntityFactory;
import org.example.entities.Entity;
import org.example.entities.Predator;

public class PredatorsFactory implements EntityFactory {

    @Override
    public Entity create(Coordinates coordinates) {
        return new Predator(coordinates.getN(), coordinates.getM());
    }
}
