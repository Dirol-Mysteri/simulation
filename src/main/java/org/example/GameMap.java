package org.example;

import org.example.entities.Entity;

import java.util.HashMap;

public class GameMap {

    private final int n;
    private final int m;

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();


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

    public int getM() {
        return m;
    }
}
