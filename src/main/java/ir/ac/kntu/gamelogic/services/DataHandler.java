package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Player;
import ir.ac.kntu.gamelogic.models.tanks.*;
import ir.ac.kntu.gamelogic.models.terrains.BrickWall;
import ir.ac.kntu.gamelogic.models.terrains.Flag;
import ir.ac.kntu.gamelogic.models.terrains.IronWall;
import ir.ac.kntu.gamelogic.models.terrains.Spawner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {
    private static final DataHandler INSTANCE = new DataHandler();

    private static final String PLAYER_DATA_PATH = "src/main/data/player.ser";

    private static final String MAP_DATA_PATH = "src/main/data/map.txt";

    private DataHandler() {
    }

    public static DataHandler getInstance() {
        return INSTANCE;
    }

    public List<Player> getPlayers() {
        List<Player> players;
        try (FileInputStream fileIn = new FileInputStream(PLAYER_DATA_PATH);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            players = (ArrayList<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void savePlayers(List<Player> players) {
        try (FileOutputStream fileOut = new FileOutputStream(PLAYER_DATA_PATH);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameObject> loadGameObjectsFromFile() {
        List<GameObject> gameObjects = new ArrayList<>();
        File file = new File(MAP_DATA_PATH);
        int maxRow = 0;
        int maxColumn = 0;
        try (Scanner scanner = new Scanner(file)) {
            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                maxColumn = Math.max(maxColumn, line.length());
                processLine(line, y, gameObjects);
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

    private void processLine(String line, int y, List<GameObject> gameObjects) {
        for (int x = 0; x < line.length(); x++) {
            char character = line.charAt(x);
            GameObject gameObject = createGameObject(character, (x + 1.5) * GameVariables.TILE_SIZE,
                    (y + 1.5) * GameVariables.TILE_SIZE);
            if (gameObject != null) {
                gameObjects.add(gameObject);
            }
            handlePlayerTanks(character, gameObject);
            handleRemainingTanks(gameObject);
            removePlayerTankIfExists(gameObject, gameObjects);
        }
    }

    private void handlePlayerTanks(char character, GameObject gameObject) {
        if (character == 'P' && gameObject instanceof PlayerTank playerTank && GameVariables.playerTank1 == null) {
            GameVariables.playerTank1 = playerTank;
        }
        if (character == 'p' && gameObject instanceof PlayerTank playerTank && GameVariables.playerTank2 == null) {
            GameVariables.playerTank2 = playerTank;
        }
    }

    private void handleRemainingTanks(GameObject gameObject) {
        if (gameObject instanceof EnemyTank) {
            --GameVariables.remainingTanks;
        }
    }

    private void removePlayerTankIfExists(GameObject gameObject, List<GameObject> gameObjects) {
        if (gameObject instanceof PlayerTank playerTank) {
            gameObjects.remove(playerTank);
        }
    }


    private GameObject createGameObject(char character, double x, double y) {
        return switch (character) {
            case 'P' -> new PlayerTank(x, y);
            case 'p' -> GameVariables.isTwoPlayer ? new PlayerTank(x, y) : null;
            case 'O' -> new RegularTank(x, y);
            case 'A' -> new ArmoredTank(x, y);
            case 'c' -> new RegularLuckyTank(x, y);
            case 'C' -> new ArmoredLuckyTank(x, y);
            case 'B' -> new BrickWall(x, y);
            case 'M' -> new IronWall(x, y);
            case 'F' -> new Flag(x, y);
            case 'S' -> new Spawner(x, y);
            default -> null;
        };
    }
}
