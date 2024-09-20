package org.example;

import org.example.entities.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameMap {
//    private static final double PERCENT_OF_PREDATORS = 0.10;
//    private static final double PERCENT_OF_HERBIVORES = 0.15;
//    private static final double PERCENT_OF_RESOURCES = 0.20;
//    private static final double PERCENT_OF_STATIC_OBJECTS = 0.10;

    private final int n;
    private final int m;
//    private final int maxPredators;
//    private final int maxHerbivores;
//    private final int maxResources;
//    private final int maxStaticObjects;
    private final HashMap<Coordinates, Entity> entities = new HashMap<Coordinates, Entity>();
//    private final Set<Coordinates> unoccupiedCells = new HashSet<>();
//    private final Random random = new Random();

    public GameMap(int n, int m) {
        this.n = n;
        this.m = m;
        int mapSize = n * m;
//        this.maxPredators = (int) (mapSize * PERCENT_OF_PREDATORS);
//        this.maxHerbivores = (int) (mapSize * PERCENT_OF_HERBIVORES);
//        this.maxResources = (int) (mapSize * PERCENT_OF_RESOURCES);
//        this.maxStaticObjects = (int) (mapSize * PERCENT_OF_STATIC_OBJECTS);

        // Populate the unoccupiedCells set with all possible coordinates

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                unoccupiedCells.add(new Coordinates(i, j));
//            }
//        }
//
//        this.setDefaultPositions();

    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    //    private void fillFreeSpace() {
//        for (Coordinates coordinates : this.unoccupiedCells) {
//            Entity freeSpace = new FreeSpace(coordinates.getN(), coordinates.getM());
//            entities.put(coordinates, freeSpace);
//        }
//    }
//
//    private void fillEntities(int maxCount, EntityFactory factory) {
//        int i = 0;
//        while (i < maxCount) {
//            Coordinates coordinates = new Coordinates(random.nextInt(this.n), random.nextInt(this.m));
//            if (!entities.containsKey(coordinates)) {
//                entities.put(coordinates, factory.create(coordinates));
//                unoccupiedCells.remove(coordinates);
//                i++;
//            }
//        }
//    }
//
//    private void setDefaultPositions() {
//        fillEntities(maxHerbivores, (coordinates) -> new Herbivore(coordinates.getN(), coordinates.getM()));
//        fillEntities(maxPredators, (coordinates) -> new Predator(coordinates.getN(), coordinates.getM()));
//        fillEntities(maxResources, (coordinates) -> new Grass(coordinates.getN(), coordinates.getM()));
//        fillEntities(maxStaticObjects, (coordinates) -> new StaticObject(coordinates.getN(), coordinates.getM()));
//        fillFreeSpace();
//    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}
