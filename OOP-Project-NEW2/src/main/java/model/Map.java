package model;

public class Map {
    private static Map instance;
    private static Area area = Area.getInstance();
    private int row = Hex.totalInRow;
    private int col = Hex.totalInCol;
    public static Player[] players = new Player[2];
    public static Player current_player;

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void getDefaultPlayerArea() {
        area.getDefaultMap(players);
    }

    private void showCurrentPlayerStatus() {
        System.out.println("Name : " + current_player.name + "  |  budget: " + current_player.budget + "   * * * *");
    }

    public void generateNewMap(Player current_player) {
        getCurrentPlayer(current_player);
        showCurrentPlayerStatus();
        System.out.println("--------------------------------------");
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (Hex.isOwner() && Hex.hasMinion()) {
                    System.out.print("  M  ");
                } else if (Hex.isOwner() && Hex.hasMinion() == false) {
                    System.out.print("  " + Hex.playerNumber + "  ");
                } else {
                    System.out.print("  *  ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }


    private void getCurrentPlayer(Player current_player) {
        this.current_player = current_player;
    }

}
