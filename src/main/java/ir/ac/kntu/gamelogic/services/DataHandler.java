package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
}
