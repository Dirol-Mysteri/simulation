package org.example.utils;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.EntityType;

import java.util.*;

public class FindPathWithBFS {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static List<Coordinates> findPath(GameMap map, Creature caller, EntityType targetType) {

        HashMap<Coordinates, Entity> entities = map.getEntities();

        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        Coordinates start = caller.getCoordinates();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            // Checking all directions
            for (int[] direction : DIRECTIONS) {
                Coordinates neighborCell = new Coordinates(current.getN() + direction[0], current.getM() + direction[1]);

                if (visited.contains(neighborCell) || isOutOffMapBounds(neighborCell, map)) {
                    continue;
                }

                Entity neighborEntity = entities.get(neighborCell);

                // If a neighbor cell isn't the target
                if (neighborEntity == null) {
                    queue.add(neighborCell);
                    visited.add(neighborCell);
                    cameFrom.put(neighborCell, current);
                    continue;
                }

                // If the target is found
                if (neighborEntity.isType(targetType)) {
                    cameFrom.put(neighborCell, current);
                    return reconstructPath(cameFrom, start, neighborCell);
                }
            }

        }
        // If no target is found
        return new ArrayList<>();
    }

    // Reconstructing the Path
    private static List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private static boolean isOutOffMapBounds(Coordinates coordinates, GameMap map) {
        return coordinates.getN() < 0 || coordinates.getN() >= map.getN() ||
                coordinates.getM() < 0 || coordinates.getM() >= map.getM();
    }
}
