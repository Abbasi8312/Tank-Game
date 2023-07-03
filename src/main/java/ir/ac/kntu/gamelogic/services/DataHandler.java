package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.Flag;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Player;
import ir.ac.kntu.gamelogic.models.tanks.*;
import ir.ac.kntu.gamelogic.models.terrains.BrickWall;
import ir.ac.kntu.gamelogic.models.terrains.IronWall;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {
    private final static DataHandler INSTANCE = new DataHandler();

    private final String path = "src/main/data/player.ser";

    private DataHandler() {
    }

    public static DataHandler getINSTANCE() {
        return INSTANCE;
    }

    public List<Player> getPlayers() {
        List<Player> players;
        try (FileInputStream fileIn = new FileInputStream(path); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            players = (ArrayList<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void savePlayers(List<Player> players) {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameObject> loadGameObjectsFromFile() {
        List<GameObject> gameObjects = new ArrayList<>();
        File file = new File("src/main/data/map.txt");
        int maxRow = 0;
        int maxColumn = 0;
        try (Scanner scanner = new Scanner(file)) {
            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                maxColumn = Math.max(maxColumn, line.length());
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    GameObject gameObject = createGameObject(character, (x + 1.5) * GameVariables.TILE_SIZE,
                            (y + 1.5) * GameVariables.TILE_SIZE);
                    if (gameObject != null) {
                        gameObjects.add(gameObject);
                    }
                }
                y++;
            }
            maxRow = y;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameVariables.gameWidth = GameVariables.TILE_SIZE * (maxColumn + 2);
        GameVariables.gameHeight = GameVariables.TILE_SIZE * (maxRow + 2);
        return gameObjects;
    }

    private GameObject createGameObject(char character, double x, double y) {
        return switch (character) {
            case 'P' -> new PlayerTank(x, y);
            case 'O' -> new RegularTank(x, y);
            case 'A' -> new ArmoredTank(x, y);
            case 'c' -> new RegularLuckyTank(x, y);
            case 'C' -> new ArmoredLuckyTank(x, y);
            case 'B' -> new BrickWall(x, y);
            case 'M' -> new IronWall(x, y);
            case 'F' -> new Flag(x, y);
            default -> null;
        };
    }
}
