package org.example.utils;

import org.example.Coordinates;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.EntityType;

import java.util.*;

public class FindPathWithBFS {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static List<Coordinates> findPath(HashMap<Coordinates, Entity> map, Creature caller, EntityType targetType) {
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        Coordinates start = caller.getCoordinates();
        queue.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            visited.add(current);
            // Checking all directions
            for (int[] direction : DIRECTIONS) {
                Coordinates neighbor = new Coordinates(current.getN() + direction[0], current.getM() + direction[1]);

                if (visited.contains(neighbor) || !map.containsKey(neighbor)) {
                    continue;
                }

                Entity neighborEntity = map.get(neighbor);

                // If the target is found
                if (neighborEntity.isType(targetType)) {
                    cameFrom.put(neighbor, current);
                    return reconstructPath(cameFrom, start, neighbor);
                }

                // If a neighbor cell isn't the target
                if (neighborEntity.getEntityType() == EntityType.FREE_SPACE) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        // If no target is found
        return null;
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
}
