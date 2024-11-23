package org.example;

import org.example.actions.Action;
import org.example.actions.MakeMoves;
import org.example.actions.SetPositions;
import org.example.enums.ActionType;
import org.example.utils.CLI;
import org.example.utils.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private static final int DEFAULT_MAP_HEIGHT = 20;
    private static final int DEFAULT_MAP_WIDTH = 20;
    private static final Scanner scanner = new Scanner(System.in);
    private static Simulation instance;
    private final Renderer renderer;
    private final CLI CLI;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private GameMap gameMap;
    private int movesCounter;
    private volatile boolean isRunning;
    private volatile boolean isActiveBackgroundInput;
    private volatile boolean isEnded;

    Simulation(int height, int width) {
        this.gameMap = new GameMap(height, width);
        this.renderer = new Renderer(gameMap);
        this.CLI = new CLI(this);
        this.movesCounter = 0;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
        this.isRunning = false;
        this.isActiveBackgroundInput = false;
    }

    public static void run() {
        if (instance == null) {
            instance = new Simulation(DEFAULT_MAP_HEIGHT, DEFAULT_MAP_WIDTH);
        }
        // Поток для симуляции
        Thread simulationThread = new Thread(instance::startSimulation);


        // Поток для обработки ввода
        Thread inputThread = new Thread(instance::handleInput);

        simulationThread.start();
        inputThread.start();

        try {
            simulationThread.join();
            inputThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scanner.close();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void startSimulation() {
        synchronized (scanner) {
            CLI.startMenu();
            addAction(new SetPositions(gameMap));
            addAction(new MakeMoves(gameMap));
            for (Action action : initActions) {
                action.execute();
            }
            isRunning = true;
            isActiveBackgroundInput = true;
        }
        renderer.render();
        while (isRunning) {
            nextTurn();
        }
        if (!isEnded) {
            pauseSimulation();
        }
    }

    public void nextTurn() {
        movesCounter++;
        for (Action action : turnActions) {
            action.execute();
        }
        renderer.render();
        if (gameMap.getHerbivoresQTY() < 1) {
            isActiveBackgroundInput = false;
            isRunning = false;
            isEnded = true;
            System.out.println(movesCounter);
            System.out.println("Симуляция завершена!");
        }

        if (!isRunning && !isEnded) {
            CLI.pauseMenu();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseSimulation() {
        isRunning = false;
        isActiveBackgroundInput = false;
        CLI.pauseMenu();
    }

    public void resumeSimulation() {
        isRunning = true;
        isActiveBackgroundInput = true;
        renderer.render();
        while (isRunning) {
            nextTurn();
        }
        if (!isEnded) {
            pauseSimulation();
        }
    }

    public void endSimulation() {
        isRunning = false;
        isActiveBackgroundInput = false;
        isEnded = true;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    protected void addAction(Action action) {
        if (action.getActionType() == ActionType.INIT_ACTION) {
            initActions.add(action);
        } else {
            turnActions.add(action);
        }
    }

    void handleInput() {
        while (!isEnded) {
            if (isActiveBackgroundInput) {
                synchronized (scanner) {
                    if (scanner.hasNextLine()) {
                        isRunning = false;
                        isActiveBackgroundInput = false;
                        scanner.nextLine(); // Очистка буффера
                        try {
                            Thread.currentThread().sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
