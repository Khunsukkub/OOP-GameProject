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
        playerNow.buyHex();
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
}
