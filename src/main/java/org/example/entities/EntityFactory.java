package org.example.entities;

import org.example.Coordinates;

@FunctionalInterface
public interface EntityFactory {
    Entity create(Coordinates coordinates);
}
