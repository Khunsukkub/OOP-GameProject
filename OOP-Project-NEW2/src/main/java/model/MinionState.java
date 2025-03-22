package model;

import java.util.ArrayList;
import java.util.List;

public class MinionState {
    private static MinionState instance;
    private static List<Minion> player1Minion = new ArrayList<>();
    private static List<Minion> player2Minion = new ArrayList<>();

    public static MinionState getInstance() {
        if (instance == null) {
            instance = new MinionState();
        }
        return instance;
    }

    public static void addMinion(int playerNumber, Minion minion) {
        if(playerNumber == 1) player1Minion.add(minion);
        else if(playerNumber == 2) player2Minion.add(minion);
    }
}
