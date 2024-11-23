package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.HashMap;
import java.util.List;

public abstract class Creature extends Entity {
    int speed;
    int hp;
    String sprite;
    private EntityType targetEntity;

    public Creature(int positionN, int positionM, EntityType entityType) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = entityType;
        this.targetEntity = determineTargetEntity(entityType);
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
            interactWithTarget(target, gameMap);
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

    private EntityType determineTargetEntity(EntityType entityType) {
        return entityType == EntityType.PREDATOR ? EntityType.HERBIVORE : EntityType.RESOURCE;
    }

    public abstract void interactWithTarget(Entity target, GameMap gameMap);

}
