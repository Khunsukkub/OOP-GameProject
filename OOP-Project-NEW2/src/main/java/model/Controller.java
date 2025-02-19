package model;

import java.util.Scanner;

public class Controller {
    private static Controller instance;
    private static MainGame mainGame = MainGame.getInstance();
    private static Player current_player;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private static void getPlayer(Player player) {
        current_player = player;
    }

    public static void show(Player player1) {
        getPlayer(player1);
        System.out.println("(  BuyMinion  /" + "  BuyHex  /" + "  END  )");
        actionSystem(player1);
    }
    public static void actionSystem(Player player1) {
        System.out.print("Please enter your action : " );
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();

        action = action.toLowerCase();

        switch (action) {
            case "buyminion":
                if(MainGame.spawn_lefts != 0) buyMinion(current_player);
                show(player1);
                break;
            case "buyhex":
//                buyHex(current_player);
//                show(current_player);
                  break;
            case "end" :
//                endTurn();
        }
    }

    public static void buyMinion(Player player1) {
        System.out.println("Minion in stock : ");
        for(int i = 0; i < MainGame.minionList.length ; i++) {
            System.out.println("   " + MainGame.minionList[i].name + "  |  cost:" + MainGame.minionList[i].spawn_cost);
        }
        System.out.println("EXIT");
        System.out.print("Please enter minion you want to buy : " );
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        for(int i = 0; i < MainGame.minionList.length ; i++) {
            if(isEnoughBudget(action , i)) {
                current_player.addMinion(MainGame.minionList[i]);
                current_player.budget -= MainGame.minionList[i].spawn_cost;
                MainGame.spawn_lefts--;
                System.out.println(current_player + " bought " + " " + MainGame.minionList[i].name + "!!" );
                //DeployMinion();
            } else {
                System.out.println("You don't have enough Budget/Hex/SpawnLefts to buy this minion");
            }
        }
    }

    public static boolean isEnoughBudget(String action, int index) {
        return (action.equals(MainGame.minionList[index])
                && current_player.budget >= MainGame.minionList[index].spawn_cost
                && current_player.ownMinion.length != current_player.ownHex.length
                );
    }
}
