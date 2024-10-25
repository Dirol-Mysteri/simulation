package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.CreatureType;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.List;

public abstract class Creature extends Entity {
    CreatureType typeOfCreature;
    EntityType targetType;
    int speed;
    int hp;

    public Creature(int positionN, int positionM, EntityType entityType) {
        this.speed = 1;
        this.hp = 1;
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = entityType;
        this.targetType = this.isType(EntityType.PREDATOR) ? EntityType.HERBIVORE : EntityType.PREDATOR;
    }

    void moveAlongPath(List<Coordinates> path) {
        // Перемещаемся по пути, но не больше, чем на значение скорости
        int steps = Math.min(speed, path.size() - 1); // -1, потому что мы уже стоим на стартовой позиции
        if (steps <= speed) {
            // ЗДЕСЬ НАДО НАПИСАТЬ ЛОГИКУ АТАКИ НА ТАРГЕТ ЭНТИТИ
            System.out.println(this.getEntityType().name() + " attacked " + this.targetType.name());
        }
        Coordinates newPosition = path.get(steps);
        this.setPosition(newPosition);
//        System.out.println(this.getEntityType().name() + " переместился на (" + newPosition.getN() + ", " + newPosition.getM() + ")");
    }


    void setPosition(Coordinates newPosition) {
        setCoordinates(newPosition);
    }


    public void makeMove(GameMap gameMap) {
        List<Coordinates> path = FindPathWithBFS.findPath(gameMap.getEntities(), this, this.targetType);
        if (path != null) {
            this.moveAlongPath(path);
        }
    }
}
