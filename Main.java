public class Main {
    public static int turnInterest = 100;
    Player playerNow;
    public int Turn = 0;

    public void mainGame(Player player1, Player player2) {
        while(Turn != 50) {
            checkPlayerTurn(player1 , player2);
            StartPhase(player1,player2);
            hexPhase();
            EndTurn();
            playerToggle(player1,player2);
        }
    }

    private void playerToggle(Player player1 , Player player2) {
        if(player1.playerState) {
            player1.playerState = false;
            player2.playerState = true;
        } else {
            player1.playerState = true;
            player2.playerState = false;
        }
        checkPlayerTurn(player1 , player2);
    }

    private void StartPhase(Player player1 , Player player2) {
        giveCoin(playerNow);
    }

    private void EndTurn() {
        Turn++;
    }

    private void hexPhase() {
        playerNow.phase = "BuyHex";
        Hex hexToBuy = getAvailableHex(); // Dummy method to get a Hex
        if (hexToBuy != null) {
            playerNow.buyHex(hexToBuy);
        }
    }

    private void giveCoin(Player playerNow) {
        playerNow.UpdatedStats();
    }

    private void checkPlayerTurn(Player player1 , Player player2) {
        playerNow = (player1.isPlayerTurn())? player1 : player2;
    }

    private int SpeedUP(){
        return turnInterest+(turnInterest/2);
    }

    private Hex getAvailableHex() {
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < hexGrid[i].length; j++) {
                if (hexGrid[i][j].owner == null) {
                    return hexGrid[i][j]; // คืนค่า Hex ที่ไม่มีเจ้าของ
                }
            }
        }
        return null; // ไม่มี Hex ว่างเหลือ
    }
}
