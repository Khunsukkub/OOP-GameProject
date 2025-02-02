package Eiei;

public class Main {
    public static int turnInterest = 100;
    Player playerNow;
    public int Turn = 0;

    public void mainGame(Player player1, Player player2) {
        while(Turn != 50) { // เอาไว้ set turn ทั้งเกม
            checkPlayerTurn(player1 , player2);
            StartPhase();
            hexPhase(); //50%
            //buyMinionPhase(); // เอาไว้ซื้อ minion
            //minionPhase(); // เอาไว้ ใช้ minion
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

    private void StartPhase() {
        giveCoin(playerNow);
    }

    private void EndTurn() {
        Turn++;
    }

    private void hexPhase() { // ช่วยแก้ตรงนี้หน่อย
        playerNow.phase = "BuyHex";
        if (hexToBuy != null) {
            playerNow.buyHex(hexToBuy); // ไม่รู้ว่า จะจัดการยังไงให้ ซื้อ Hex ที่ต้องการได้ แต่เดี๋ยวจะตรวจตรงนี้ให้
        }
    }

    private void giveCoin(Player playerNow) { //ให้เงินตัวละคร
        playerNow.UpdatedStats();
    }

    private void checkPlayerTurn(Player player1 , Player player2) { // เช็ค ตาผู้เล่น
        playerNow = (player1.isPlayerTurn())? player1 : player2;
    }

    private int SpeedUP(){ // เอาไว้ เร่งเทิร์น
        return turnInterest+(turnInterest/2);
    }
}
