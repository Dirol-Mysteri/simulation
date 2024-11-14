package org.example.utils;

import org.example.GameMap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    private static final int MAIN_MENU_MAX_CHOICE = 2;
    private static final int MAP_SETTINGS_MAX_CHOICE = 3;
    private static final int SETTINGS_MAX_CHOICE = 2;

    private final GameMap gameMap;
    private final Scanner scanner;

    public CLI(GameMap gameMap) {
        this.gameMap = gameMap;
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        // Сюда надо прописать бесконечный цикл, с выходом после старта симуляции.
        System.out.println();
        System.out.println("\nДобро пожаловать в пошаговую симуляцию 2D мира!\n");
        System.out.println("1 - Запустить симуляцию.");
        System.out.println("2 - Настроить параметры симуляции.\n");
        System.out.print("Введите число: ");
        int choice = getUserChoice(MAIN_MENU_MAX_CHOICE);
        if (choice == 1) {
            startSimulation();
            // Здесь надо будет нарушить true условие
            // И надо будет подумать над выходом из потока
        } else {
            settings();
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

    private void settings() {
        System.out.println();
        System.out.println("Выберите параметры для настройки:");
        System.out.println();
        System.out.println("Введите \"1\", чтобы настроить размер карты.");
        System.out.println("Введите \"2\", чтобы настроить количество существ.");
        int choice = getUserChoice(SETTINGS_MAX_CHOICE);
        if (choice == 1) {
            mapSettings();
        } else {
            entitiesSettings();
        }
    }

    private void mapSettings() {
        System.out.println("\nВведите размер карты по высоте:");
        int userMapHeightChoice = getPositiveInt();

        System.out.println("\nВведите размер карты по ширине:");
        int userMapWidthChoice = getPositiveInt();

        gameMap.setN(userMapHeightChoice);
        gameMap.setM(userMapWidthChoice);

        System.out.println();
        System.out.println("\nРазмеры карты заданы!");
        System.out.println();
        System.out.println("Нажмите \"1\" чтобы настроить размеры карты снова.");
        System.out.println("Нажмите \"2\" чтобы перейти к настройкам существ.");
        System.out.println("Нажмите \"3\" чтобы вернуться в главное меню.");

        int choice = getUserChoice(MAP_SETTINGS_MAX_CHOICE);
        switch (choice) {
            case 1 -> mapSettings();
            case 2 -> entitiesSettings();
            case 3 -> mainMenu();
        }
    }

    private void entitiesSettings() {

    }

    public void close() {
        scanner.close();
    }

    private int getUserChoice(int maxChoice) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Пожалуйста, выберите вариант от 1 до " + maxChoice + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.next(); // очистка некорректного ввода
            }
        }
    }

    private int getPositiveInt() {
        while (true) {
            try {
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