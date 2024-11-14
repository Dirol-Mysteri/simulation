package org.example;

import org.example.entities.Entity;

import java.util.HashMap;

public class GameMap {

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();
    private int n;
    private int m;
    private int herbivoresQuantity;
    private int predatorsQuantity;
    private int resourcesQuantity;


    public GameMap(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

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

    public int getHerbivoresQuantity() {
        return herbivoresQuantity;
    }

    public void setHerbivoresQuantity(int herbivoresQuantity) {
        this.herbivoresQuantity = herbivoresQuantity;
    }

    public int getPredatorsQuantity() {
        return predatorsQuantity;
    }

    public void setPredatorsQuantity(int predatorsQuantity) {
        this.predatorsQuantity = predatorsQuantity;
    }

    public int getResourcesQuantity() {
        return resourcesQuantity;
    }

    public void setResourcesQuantity(int resourcesQuantity) {
        this.resourcesQuantity = resourcesQuantity;
    }
}
