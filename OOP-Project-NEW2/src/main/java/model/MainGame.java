package model;

import java.util.Random;

public class MainGame {
    private static MainGame instance;
    private static Map map = Map.getInstance();
    private static Controller controller = Controller.getInstance();
    private static int minionNumber;
    public static int current_turn = 1;
    public static int max_turns = 69;
    public static double init_budget = 10000;
    private static double turn_budget = 90;
    private static double max_budget = 23456;
    public static int max_spawns = 47;
    public static int spawn_lefts = max_spawns;
    public static Minion[] minionList;
    public static Player current_player;
    public static Player opponent_player;

    static Random rand = new Random();

    public static MainGame getInstance() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public static void getFirstPlayer(Player player1, Player player2) {
        int numberOfCurrentPlayer = rand.nextInt(2) + 1;
        current_player = (numberOfCurrentPlayer == 1)? player1 : player2;
        opponent_player = (current_player == player1)? player2 : player1;
    }

    private void togglePlayer(){
        Player temp = current_player;
        current_player = opponent_player;
        opponent_player = temp;
    }

    public void endTurn(){
        current_turn++;
        double turnIncome = turnInterest(opponent_player, current_turn);
        System.out.println("--------------------------------------");
        System.out.println(opponent_player.name + " got " + turnIncome + " !!");
        System.out.println("--------------------------------------");
        togglePlayer();
        GameDisplay();
    }

    public double turnInterest (Player player , int current_turn) {
        return player.budget += Interest.pct(player.budget,current_turn);
    }

    public static void GameSetting(Player player1, Player player2) {
        minionNumber = MinionChoosing.chooseMinionNumber();
        MinionSetting.design(minionNumber);
        getFirstPlayer(player1,player2);
        SetDefaultMap();
        SetDefaultPlayerArea();
        GameDisplay();
    }

    public static void GameDisplay() {
        showController(current_player);
    }

    public static void SetDefaultMap() {
        map.initializeMap();
    }

    private static void SetDefaultPlayerArea() {
        map.getDefaultPlayerArea();
    }

    private static void showController(Player current_player) {
        Controller.show(current_player);
    }

    private static void showSpawnLeft() {
        System.out.println(spawn_lefts);
    }

    public static void NewMapGenarate() {
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
