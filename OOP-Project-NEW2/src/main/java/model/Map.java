package model;

public class Map {
    private static Map instance;
    private static MainGame mainGame;
    private Hex[][] map;
    private int row = Hex.totalInRow;
    private int col = Hex.totalInCol;
    public static Player[] players = new Player[2];
    public static Player current_player;

    int[] init_player1Area = {0, 1, 2, 8, 9, 16};
    int[] init_player2Area = {47, 54, 55, 61, 62, 63};
    int[] total_init_playerArea = {0, 1, 2, 8, 9, 16, 47, 54, 55, 61, 62, 63};

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public Hex getHexAt(int row, int col) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            return null; // ป้องกัน IndexOutOfBoundsException
        }
        return map[row][col]; // ดึง Hex ที่ตำแหน่ง (row, col)
    }


    public void getDefaultPlayerArea() {
        // กำหนดพื้นที่สำหรับผู้เล่น 1 (player 1)
        for (int i = 0; i < init_player1Area.length; i++) {
            int row = init_player1Area[i] / 8;
            int col = init_player1Area[i] % 8;
            map[row][col].setOwner(players[0]); // ผู้เล่นคนแรก
        }

        // กำหนดพื้นที่สำหรับผู้เล่น 2 (player 2)
        for (int i = 0; i < init_player2Area.length; i++) {
            int row = init_player2Area[i] / 8;
            int col = init_player2Area[i] % 8;
            map[row][col].setOwner(players[1]); // ผู้เล่นคนที่สอง
        }
    }


    private void showCurrentPlayerStatus() {
        System.out.println("Name : " + current_player.name + " ( player" + current_player.Number + " )" + "  |  budget: " + current_player.budget + "   * * * *");
    }

    public void initializeMap() {
        map = new Hex[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = new Hex(i,j);
            }
        }
    }

    public void generateNewMap(Player current_player) {
        System.out.println("--------------------------------------");
        getCurrentPlayer(current_player);
        showCurrentPlayerStatus();
        System.out.println("Turn "
                + MainGame.current_turn + "/" + MainGame.max_turns + "  |  "
                + "Spawn lefts " + MainGame.spawn_lefts);
        System.out.println("--------------------------------------");
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                Hex hex = map[i][j];
                if (hex.isOwner() && hex.hasMinion()) {
                    System.out.print("  M  ");
                } else if (hex.isOwner() && !hex.hasMinion()) {
                    System.out.print("  " + hex.playerNumber + "  ");
                } else {
                    System.out.print("  *  ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }


    private void getCurrentPlayer(Player current_player) {
        Map.current_player = current_player;
    }

}
