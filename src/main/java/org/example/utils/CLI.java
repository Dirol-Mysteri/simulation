package org.example.utils;

import org.example.GameMap;
import org.example.Simulation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    private static final int MAIN_MENU_MAX_CHOICE = 2;
    private static final int SETTINGS_MENU_MAX_CHOICE = 2;

    private final Simulation simulation;
    private final GameMap gameMap;
    private final Scanner scanner;
    private boolean isActive = true;

    public CLI(Simulation simulation) {
        this.simulation = simulation;
        this.gameMap = simulation.getGameMap();
        this.scanner = Simulation.getScanner();
    }

    public void startMenu() {
        Renderer.clearConsole();
        System.out.println("\nДобро пожаловать в пошаговую симуляцию 2D мира!\n");
        showMainMenu();
    }

    public void mainMenu() {
        Renderer.clearConsole();
        showMainMenu();
    }

    private void showMainMenu() {
        while (isActive) {
            System.out.println();
            System.out.println("1 - Запустить симуляцию");
            System.out.println("2 - Настроить параметры симуляции\n");
            System.out.print("Введите число: ");
            int choice = getUserChoice(MAIN_MENU_MAX_CHOICE);
            if (choice == 1) {
                isActive = false;
            } else {
                settingsMenu();
            }
        }
    }


    private void startSimulation() {
        System.out.println("Запускаем симуляцию!");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Симуляция была прервана.");
        }
    }

    private void settingsMenu() {
        Renderer.clearConsole();
        System.out.println();
        System.out.println("Выберите параметры для настройки:");
        System.out.println();
        System.out.println("Введите \"1\", чтобы настроить размер карты");
        System.out.println("Введите \"2\", чтобы настроить количество существ");
        System.out.print("\nВведите число: ");
        int choice = getUserChoice(SETTINGS_MENU_MAX_CHOICE);
        if (choice == 1) {
            mapSettingsMenu();
        } else {
            entitiesSettings();
        }
    }

    private void mapSettingsMenu() {
        System.out.print("\nВведите размер карты по высоте: ");
        int userMapHeightChoice = getPositiveInt();

        System.out.print("\nВведите размер карты по ширине: ");
        int userMapWidthChoice = getPositiveInt();

        gameMap.setM(userMapWidthChoice);
        gameMap.setN(userMapHeightChoice);
        gameMap.setMapSize(userMapHeightChoice * userMapWidthChoice);
        gameMap.resetEntities();


        System.out.println();
        System.out.println("\nРазмеры карты заданы!");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Симуляция была прервана.");
        }
    }

    public void pauseMenu() {
        System.out.println("Игра приостановлена! Выберите опцию:");
        System.out.println("1 - Продолжить");
        System.out.println("2 - Сделать всего 1 ход");
        System.out.println("3 - Выйти из игры");
        int option = getUserChoice(3);
        switch (option) {
            case 1 -> {
                simulation.resumeSimulation();
                return;
            }
            case 2 -> {
                simulation.nextTurn();
                return;
            }
            default -> simulation.endSimulation();
        }
        System.out.println("Вы вышли из игры.");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Симуляция была прервана.");
        }
    }

    private void entitiesSettings() {
        Renderer.clearConsole();
        System.out.println("\nКоличество хищников\n");
        System.out.print("Введите число от 1 до " + gameMap.getMaxPredatorsQTY() + ": ");
        int predatorsChoice = getUserChoice(gameMap.getMaxPredatorsQTY());
        gameMap.setPredatorsQTY(predatorsChoice);

        System.out.println("\nКоличество травоядных\n");
        System.out.print("Введите число от 1 до " + gameMap.getMaxHerbivoresQTY() + ": ");
        int herbivoresChoice = getUserChoice(gameMap.getMaxHerbivoresQTY());
        gameMap.setHerbivoresQTY(herbivoresChoice);

        System.out.println("\nКоличество ресурсов для травоядных\n");
        System.out.print("Введите число от 1 до " + gameMap.getMaxResourcesQTY() + ": ");
        int resourcesChoice = getUserChoice(gameMap.getMaxResourcesQTY());
        gameMap.setResourcesQTY(resourcesChoice);

        System.out.println("\nКоличество статичных объектов\n");
        System.out.print("Введите число от 1 до " + gameMap.getMaxStaticObjectsQTY() + ": ");
        int staticObjectsChoice = getUserChoice(gameMap.getMaxStaticObjectsQTY());
        gameMap.setStaticObjectsQTY(staticObjectsChoice);

        System.out.println("\nНовые параметры успешно заданы!");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Симуляция была прервана.");
        }
    }

    private int getUserChoice(int maxChoice) {
        synchronized (scanner) {
            while (true) {
                try {
                    if (scanner.hasNextLine()) {
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Очистка буфера
                        if (choice >= 1 && choice <= maxChoice) {
                            return choice;
                        } else {
                            System.out.println("Пожалуйста, выберите вариант от 1 до " + maxChoice + ".");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Пожалуйста, введите корректное число.");
                    scanner.next(); // очистка некорректного ввода
                }
            }
        }
    }

    private int getPositiveInt() {
        synchronized (scanner) {
            while (true) {
                try {
//                    scanner.next();
                    int value = scanner.nextInt();
                    if (value > 0) return value;
                    else System.out.println("Пожалуйста, введите положительное число.");
                } catch (InputMismatchException e) {
                    System.out.println("Пожалуйста, введите корректное число.");
                    scanner.next(); // очистка некорректного ввода
                }
            }
        }
    }

}