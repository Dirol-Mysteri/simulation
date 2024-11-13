package org.example.actions;

import org.example.Action;
import org.example.Coordinates;
import org.example.EntityFactory;
import org.example.GameMap;
import org.example.entities.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetDefaultPositions implements Action {

    //    private static final double PERCENT_OF_PREDATORS = 0.10;
    private static final double PERCENT_OF_PREDATORS = 0;
    private static final double PERCENT_OF_HERBIVORES = 0.15;
    private static final double PERCENT_OF_RESOURCES = 0.20;
    private static final double PERCENT_OF_STATIC_OBJECTS = 0.10;

    private final int maxPredators;
    private final int maxHerbivores;
    private final int maxResources;
    private final int maxStaticObjects;

    private final GameMap map;

    private final Random random = new Random();

    private final Set<Coordinates> unoccupiedCells = new HashSet<>();
    private final HashMap<Coordinates, Entity> entities;

    public SetDefaultPositions(GameMap map) {
        int mapSize = map.getM() * map.getM();

        this.map = map;
        this.entities = map.getEntities();

        this.maxPredators = (int) (mapSize * PERCENT_OF_PREDATORS);
        this.maxHerbivores = (int) (mapSize * PERCENT_OF_HERBIVORES);
        this.maxResources = (int) (mapSize * PERCENT_OF_RESOURCES);
        this.maxStaticObjects = (int) (mapSize * PERCENT_OF_STATIC_OBJECTS);

        for (int i = 0; i < map.getN(); i++) {
            for (int j = 0; j < map.getM(); j++) {
                unoccupiedCells.add(new Coordinates(i, j));
            }
        }
    }

    private void fillFreeSpace() {
        for (Coordinates coordinates : this.unoccupiedCells) {
            Entity freeSpace = new FreeSpace(coordinates.getN(), coordinates.getM());
            entities.put(coordinates, freeSpace);
        }
    }

    private void fillEntities(int maxCount, EntityFactory factory) {
        int i = 0;
        while (i < maxCount) {
            Coordinates coordinates = new Coordinates(random.nextInt(this.map.getN()), random.nextInt(this.map.getM()));
            if (!entities.containsKey(coordinates)) {
                entities.put(coordinates, factory.create(coordinates));
                unoccupiedCells.remove(coordinates);
                i++;
            }
        }
    }

    @Override
    public void execute() {
        fillEntities(maxHerbivores, (coordinates) -> new Herbivore(coordinates.getN(), coordinates.getM()));
        fillEntities(maxPredators, (coordinates) -> new Predator(coordinates.getN(), coordinates.getM()));
        fillEntities(maxResources, (coordinates) -> new Grass(coordinates.getN(), coordinates.getM()));
        fillEntities(maxStaticObjects, (coordinates) -> new StaticObject(coordinates.getN(), coordinates.getM()));
        fillFreeSpace();
    }
}
