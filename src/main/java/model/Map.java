package model;

import java.util.Scanner;

public class Map {
    private static Map instance;
    private static Controller controller;
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

    public void showBuyAbleHex(Player player) {
        System.out.println("--------------------------------------");
        System.out.println(player.name + " |  budget: " + player.budget + "   * * * *");
        System.out.println("--------------------------------------");

        // แสดงแผนที่พร้อมตัวเลือกการซื้อ
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Hex hex = map[i][j];

                if (hex.isOwner() && hex.hasMinion()) {
                    System.out.print("  M  ");
                } else if (hex.isOwner() && !hex.hasMinion()) {
                    System.out.print("  " + hex.playerNumber + "  ");
                } else if (canBeBoughtByPlayer(hex, player)) {
                    System.out.print("  R  ");
                } else {
                    System.out.print("  *  ");
                }
            }
            System.out.println();
        }

        System.out.println("--------------------------------------");
        // แสดงราคาของแต่ละ Hex
        System.out.println("Hex price is " + map[0][0].getHexPrice() + " each");

        // รับพิกัดที่ผู้เล่นต้องการซื้อ
        System.out.print("Which Hex you want to buy [row,col] or type 'EXIT' to quit: ");
        Scanner scanner = new Scanner(System.in);
        String hexInput = scanner.nextLine().trim();  // รับข้อมูลจากผู้เล่นและลบช่องว่าง

        if (hexInput.equalsIgnoreCase("exit")) {
            return;
        }

        try {
            String[] coordinates = hexInput.split(",");  // แยกค่าสำหรับ row และ col
            int row = Integer.parseInt(coordinates[0].trim()) - 1; // ลดลง 1 เพื่อให้เริ่มจาก 0
            int col = Integer.parseInt(coordinates[1].trim()) - 1;

            // ตรวจสอบว่า Hex ที่เลือกสามารถซื้อได้หรือไม่
            if (row >= 0 && row < this.row && col >= 0 && col < this.col) {
                Hex selectedHex = map[row][col];

                // ตรวจสอบว่าผู้เล่นสามารถซื้อ Hex นี้ได้
                if (canBeBoughtByPlayer(selectedHex, player)) {
                    if (player.budget >= selectedHex.getHexPrice()) {
                        // ถ้าผู้เล่นมีงบพอ ซื้อ Hex และเปลี่ยนเจ้าของ
                        player.budget -= selectedHex.getHexPrice();  // หักงบ
                        selectedHex.setOwner(player);  // เปลี่ยนเจ้าของ
                        player.addHex(selectedHex);
                        controller.buyHexState = true;
                        System.out.println("You bought Hex at [" + (col+1) + "," + (row+1) + "]!");
                    } else {
                        System.out.println("You don't have enough budget.");
                    }
                } else {
                    System.out.println("This Hex cannot be bought.");
                    showBuyAbleHex(player);
                }
            } else {
                System.out.println("Invalid Hex coordinates.");
                showBuyAbleHex(player);
            }
        } catch (Exception e) {
            System.out.println("Invalid input format. Please enter [row,col] (e.g., 3,2).");
            showBuyAbleHex(player);
        }
    }


    private boolean canBeBoughtByPlayer(Hex hex, Player player) {
        if (hex.isOwner()) {
            return false; // เป็นเจ้าของอยู่แล้ว ซื้อไม่ได้
        }

        // เช็คฮีกติดกัน 6 ทิศทาง (เฮกซากอน)
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},  // ซ้าย ขวา บน ล่าง
                {-1, 1}, {1, -1}  // แนวเฉียง
        };

        for (int[] dir : directions) {
            int newRow = hex.row + dir[0];
            int newCol = hex.col + dir[1];

            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col) {
                Hex neighbor = map[newRow][newCol];
                if (neighbor.isOwner() && neighbor.getOwner() == player.Number) {
                    return true; // มี Hex ที่เป็นของ player ติดกัน
                }
            }
        }
        return false;
    }


    private void getCurrentPlayer(Player current_player) {
        Map.current_player = current_player;
    }

}