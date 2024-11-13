package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Creature_copy extends Entity {
    EntityType targetEntity;
    int speed;
    int hp;
    String sprite;
    List<List<Coordinates>> previousPathes = new LinkedList<>();

    public Creature_copy(int positionN, int positionM, EntityType entityType) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = entityType;
        this.targetEntity = this.isType(EntityType.PREDATOR) ? EntityType.HERBIVORE : EntityType.RESOURCE;
    }

    void moveAlongPath(List<Coordinates> path, GameMap gameMap) {
        // Перемещаемся по пути, но не больше, чем на значение скорости
        int stepsToTarget = path.size() - 1; // -1, потому что мы уже стоим на стартовой позиции
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        if (stepsToTarget <= speed) {
            // Target attack logic
            Entity target = gameMap.getEntities().get(path.getLast());
            interactWithTarget(target, entities);
            return;
        }

        // Moving logic
        Coordinates currentPosition = this.getCoordinates();
        Coordinates newPosition = path.get(speed);
        this.setPosition(newPosition);
        gameMap.getEntities().put(currentPosition, new FreeSpace(currentPosition.getN(), currentPosition.getM()));
        gameMap.getEntities().put(newPosition, this);
    }


    void setPosition(Coordinates newPosition) {
        setCoordinates(newPosition);
    }


    public void makeMove(GameMap gameMap) {
        List<Coordinates> path = FindPathWithBFS.findPath(gameMap.getEntities(), this, this.targetEntity);
        if (path != null) {
            this.moveAlongPath(path, gameMap);
        }
    }

    public String getSprite() {
        return sprite;
    }

    public List<List<Coordinates>> getPreviousPathes() {
        return previousPathes;
    }

    public abstract void interactWithTarget(Entity target, HashMap<Coordinates, Entity> entities);

}
