package org.example.utils;

import org.example.Coordinates;
import org.example.entities.Entity;
import org.example.enums.EntityType;

import java.util.*;

public class FindPathWithBFS {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static List<Coordinates> findPath(HashMap<Coordinates, Entity> map, Entity caller, EntityType targetType) {
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
                Coordinates neighbor = new Coordinates(current.getN() + direction[0], current.getM() + direction[1]);

                if (visited.contains(neighbor) || !map.containsKey(neighbor)) {
                    continue;
                }

                Entity neighborEntity = map.get(neighbor);

//                if (neighborEntity.getEntityType() != EntityType.FREE_SPACE && neighborEntity.isType(targetType)) {
                if (neighborEntity.isType(targetType)) {
//                    System.out.println("Найдена цель на координатах: (" + neighbor.getN() + ", " + neighbor.getM() + ")");

                    // Reconstructing the Path
//                    System.out.println("cameFrom: " + cameFrom);
                    cameFrom.put(neighbor, current);
                    List<Coordinates> path = reconstructPath(cameFrom, start, neighbor);
                    return path;
                }

                if (neighborEntity.getEntityType() == EntityType.FREE_SPACE) {
                    queue.add(neighbor);
                    visited.add(neighbor);
//                    System.out.println("Adding to cameFrom:" + neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        return null; // Если цель не найдена
    }

    // Восстановление пути по карте cameFrom
    private static List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
//        System.out.println("Start: " + start);
//        System.out.println("End: " + end);
//        System.out.println("cameFrom: " + cameFrom);
        Coordinates current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
//        System.out.println(path);
        return path;
    }

    // Я, кажется, начал понимать. Вся суть тут в том, что в cameFrom добавляются все координаты, в том числе близлежащих клеток. Но когда мы находим искомую,
    // мы в методе reconstructPath из всего множества координат воостанавливаем именно нужный нам. Спроси этот момент ещё у chat GPT, чтобы наверняка материал усвоить.

}
