package org.example.utils;

import org.example.Coordinates;
import org.example.entities.Entity;

import java.util.*;

public class Searching {

//    public class BFSExample {
//        public static void main(String[] args) {
//            // Создаем граф с использованием HashMap
//            HashMap<String, List<String>> graph = new HashMap<>();
//
//            // Добавляем ребра
//            graph.put("A", Arrays.asList("B", "C"));
//            graph.put("B", Arrays.asList("A", "D", "E"));
//            graph.put("C", Arrays.asList("A", "F"));
//            graph.put("D", Arrays.asList("B"));
//            graph.put("E", Arrays.asList("B", "F"));
//            graph.put("F", Arrays.asList("C", "E"));
//
//            // Запускаем BFS
//            bfs(graph, "A");
//        }

    public static void bfs(HashMap<Coordinates, Entity> graph, Entity start) {
        // Очередь для BFS
        Queue<Entity> queue = new LinkedList<>();
        // Set для отслеживания посещённых узлов
        Set<Entity> visited = new HashSet<>();

        // Добавляем стартовую вершину в очередь
        queue.add(start);
        visited.add(start);

        // Пока очередь не пуста
        while (!queue.isEmpty()) {
            // Извлекаем вершину из очереди
            Entity current = queue.poll();
            System.out.println("Посещаем: " + current);

            // Получаем всех соседей текущей вершины
            Entity neighbors = graph.get(current);
            if (neighbors != null) {
                for (Entity neighbor : neighbors) {
                    // Если сосед не был посещен, добавляем его в очередь
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
    }

}
