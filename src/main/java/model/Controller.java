package model;

import java.util.Scanner;

public class Controller {
    private static Controller instance;
    private static MainGame mainGame = MainGame.getInstance();
    private static Map map = Map.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    public static boolean buyMinionState = false;
    public static boolean buyHexState = false;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void show(Player player) {
        MainGame.NewMapGenarate();
        System.out.println("( BuyMinion / BuyHex / END )");
        actionSystem(player);
    }

    private static void actionSystem(Player player) {
        System.out.print("Please enter your action: ");
        String action = scanner.nextLine().toLowerCase();

        if (action.equals("buyminion")) {
            if (buyMinionState) {
                System.out.println("You already bought a MINION this turn!");
                show(player);
                return;
            }
        }

        if (action.equals("buyhex")) {
            if (buyHexState) {
                System.out.println("You already bought a HEX this turn!");
                show(player);
                return;
            }
        }

        switch (action) {
            case "buyminion":
                if (MainGame.spawn_lefts != 0) buyMinion(player);
                show(player);
                break;
            case "buyhex":
                buyHex(player);
                show(player);
                break;
            case "end":
                mainGame.endTurn();
                break;
            default:
                System.out.println("Invalid action! Please try again.");
                show(player);
        }
    }

    private static void buyHex(Player player) {
        map.showBuyAbleHex(player);
        buyHexState = true;
    }

    private static void buyMinion(Player player) {
        System.out.println("Minion in stock:");
        for (Minion minion : MainGame.minionList) {
            System.out.println("   " + minion.name + "  |  cost: " + minion.spawn_cost);
        }
        System.out.println("EXIT");

        System.out.print("Please enter the minion you want to buy: ");
        String action = scanner.nextLine();

        for (int i = 0; i < MainGame.minionList.length; i++) {
            if (isEnoughBudget(action, i, player)) {
                Minion minion = MainGame.minionList[i];
                player.addMinion(minion);
                player.budget -= minion.spawn_cost;
                System.out.println(player.name + " bought " + minion.name + "!!");
                buyMinionState = true;
                deploy(minion, player);
                return;
            }
        }
        System.out.println("You don't have enough Budget/Hex/SpawnLefts to buy this minion.");
        buyMinionState = false;
    }

    private static boolean isEnoughBudget(String action, int index, Player player) {
        return (action.equalsIgnoreCase(MainGame.minionList[index].name)
                && player.budget >= MainGame.minionList[index].spawn_cost
                && player.getMinionNumber() < player.getHexNumber());
    }

    private static void deploy(Minion minion, Player player) {
        System.out.print("Choose Hex to deploy [row,col]: ");
        String hex = scanner.nextLine().trim();

        try {
            String[] coordinates = hex.split(",");
            int row = Integer.parseInt(coordinates[0].trim()) - 1;
            int col = Integer.parseInt(coordinates[1].trim()) - 1;

            Hex targetHex = map.getHexAt(row, col);

            if (targetHex == null) {
                System.out.println("Invalid Hex! Please choose a valid location.");
                deploy(minion, player);
                return;
            }
            if (targetHex.getOwner() != player.Number) {
                System.out.println("You can only deploy minions on your own Hex.");
                deploy(minion, player);
                return;
            }
            if (targetHex.hasMinion()) {
                System.out.println("This Hex already has a minion!");
                deploy(minion, player);
                return;
            }

            targetHex.setMinion(minion);
            System.out.println("Minion " + minion.name + " deployed at (" + (row + 1) + "," + (col + 1) + ")");
            MainGame.spawn_lefts--;

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter coordinates in the format: row,col");
            deploy(minion, player);
        }
    }
}