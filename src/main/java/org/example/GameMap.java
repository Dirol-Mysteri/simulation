package org.example;

import org.example.entities.Entity;

import java.util.HashMap;

public class GameMap {

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    private static final double MAX_PERCENT_OF_PREDATORS = 0.25;
    private static final double MAX_PERCENT_OF_HERBIVORES = 0.25;
    private static final double MAX_PERCENT_OF_RESOURCES = 0.25;
    private static final double MAX_PERCENT_OF_STATIC_OBJECTS = 0.25;


    private int n;
    private int m;

    private int mapSize;
    private int herbivoresQTY;
    private int predatorsQTY;
    private int resourcesQTY;
    private int staticObjectsQTY;

    public GameMap(int n, int m) {
        this.n = n;
        this.m = m;

        this.mapSize = n * m;

        this.herbivoresQTY = getMaxHerbivoresQTY();
        this.predatorsQTY = getMaxPredatorsQTY();
        this.resourcesQTY = getMaxResourcesQTY();
        this.staticObjectsQTY = getMaxStaticObjectsQTY();
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    // Getters and Setters

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getHerbivoresQTY() {
        return herbivoresQTY;
    }

    public void setHerbivoresQTY(int herbivoresQTY) {
        this.herbivoresQTY = herbivoresQTY;
    }

    public int getPredatorsQTY() {
        return predatorsQTY;
    }

    public void setPredatorsQTY(int predatorsQTY) {
        this.predatorsQTY = predatorsQTY;
    }

    public int getResourcesQTY() {
        return resourcesQTY;
    }

    public void setResourcesQTY(int resourcesQTY) {
        this.resourcesQTY = resourcesQTY;
    }

    public int getStaticObjectsQTY() {
        return staticObjectsQTY;
    }

    public void setStaticObjectsQTY(int staticObjectsQTY) {
        this.staticObjectsQTY = staticObjectsQTY;
    }

    public int getMaxHerbivoresQTY() {
        return (int) (this.mapSize * MAX_PERCENT_OF_HERBIVORES);
    }

    public int getMaxPredatorsQTY() {
        return (int) (this.mapSize * MAX_PERCENT_OF_PREDATORS);
    }

    public int getMaxResourcesQTY() {
        return (int) (this.mapSize * MAX_PERCENT_OF_RESOURCES);
    }

    public int getMaxStaticObjectsQTY() {
        return (int) (this.mapSize * MAX_PERCENT_OF_STATIC_OBJECTS);
    }

    public void resetEntities() {
        setHerbivoresQTY(getMaxHerbivoresQTY());
        setPredatorsQTY(getMaxPredatorsQTY());
        setResourcesQTY(getMaxResourcesQTY());
        setStaticObjectsQTY(getMaxStaticObjectsQTY());
    }

    public void decrementHerbivoresCount() {
        this.herbivoresQTY -= 1;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }
}
