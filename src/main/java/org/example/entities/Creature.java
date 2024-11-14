package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Creature extends Entity {
    EntityType targetEntity;
    int speed;
    int hp;
    String sprite;
    List<List<Coordinates>> previousPathes = new LinkedList<>();

    public Creature(int positionN, int positionM, EntityType entityType) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = entityType;
        this.targetEntity = this.isType(EntityType.PREDATOR) ? EntityType.HERBIVORE : EntityType.RESOURCE;
    }

    void moveAlongPath(List<Coordinates> path, GameMap gameMap) {
        // Move along the path, but no more than the speed value
        if (path.isEmpty()) {
            return;
        }
        int stepsToTarget = path.size() - 1; // -1, because we are already standing in the starting position
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        if (stepsToTarget <= speed) {
            // Logic for attacking the target
            Entity target = gameMap.getEntities().get(path.getLast());
            interactWithTarget(target, entities);
            return;
        }

        // Moving logic
        Coordinates currentPosition = this.getCoordinates();
        Coordinates newPosition = path.get(speed);
        this.setPosition(newPosition);
        gameMap.getEntities().remove(currentPosition);
        gameMap.getEntities().put(newPosition, this);
    }


    void setPosition(Coordinates newPosition) {
        setCoordinates(newPosition);
    }


    public void makeMove(GameMap gameMap) {
        List<Coordinates> path = FindPathWithBFS.findPath(gameMap, this, this.targetEntity);
        this.moveAlongPath(path, gameMap);
    }

    public String getSprite() {
        return sprite;
    }

    public abstract void interactWithTarget(Entity target, HashMap<Coordinates, Entity> entities);

}
