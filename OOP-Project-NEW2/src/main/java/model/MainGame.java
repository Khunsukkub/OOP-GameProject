package model;

import java.util.Random;

public class MainGame {
    private static MainGame instance;
    private static Map map = Map.getInstance();
    private static Controller controller = Controller.getInstance();
    private static int minionNumber;
    private int current_turn = 0;
    private static int max_turns = 69;
    public static double init_budget = 10000;
    private static double turn_budget = 90;
    private static double max_budget = 23456;
    public static int max_spawns = 47;
    public static int spawn_lefts = max_spawns;
    public static Minion[] minionList;
    public static Player current_player;

    static Random rand = new Random();

    public static MainGame getInstance() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public static void getFirstPlayer(Player player1, Player player2) {
        int numberOfCurremtPlayer = rand.nextInt(2) + 1;
        current_player = (numberOfCurremtPlayer == 1)? player1 : player2;
    }

    private void turnInterest (Player player , int current_turn) {
       player.budget += Interest.pct(player.budget,current_turn);
    }

    public static void GameSetting(Player player1, Player player2) {
        minionNumber = MinionChoosing.chooseMinionNumber();
        MinionSetting.design(minionNumber);
        getFirstPlayer(player1,player2);
        GetDefaultPlayerArea();
        NewMapGenarate();
        GameDisplay();
    }

    public static void GameDisplay() {
        showController(current_player);
    }

    private static void GetDefaultPlayerArea() {
        map.getDefaultPlayerArea();
    }

    private static void showController(Player current_player) {
        Controller.show(current_player);
    }

    private static void showSpawnLeft() {
        System.out.println(spawn_lefts);
    }

    private static void NewMapGenarate() {
        map.generateNewMap(current_player);
    }

    private void getAllSelectedMinion() {
        int i = 0;
        for (Minion minion : minionList) {
            System.out.println("Minion[" + i+1 + "] : " + minion.name);
            System.out.println("HP : " + minion.init_hp);
            System.out.println("-------------------------");
        }
    }
}
