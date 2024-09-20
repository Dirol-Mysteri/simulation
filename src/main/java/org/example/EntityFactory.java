package org.example;

import org.example.entities.Entity;

@FunctionalInterface
public interface EntityFactory {
    Entity create(Coordinates coordinates);
}
