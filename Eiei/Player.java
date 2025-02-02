package Eiei;

public class Player {
    String name;
    public boolean playerState; // True when it's the player's turn
    public int team;
    private int coin;
    public Hex[] ownHex;
    private Minion ownMinion;
    public String phase; //เฟส ในแต่ละเทิร์น

    public Player(int team, int coin) {
        this.team = team;
        this.coin = coin;
        this.ownHex = new Hex[0]; // array ไว้เก็บ hex ที่ครอบครอง
        this.playerState = (team == 1); // First player gets the turn
    }

    public void setName(String name) { //ไว้ตั้งชื่อผู้เล่น
        this.name = name;
    }

    public boolean isPlayerTurn() { //เช็คว่าผู้เล่นคนนี้เป็นผู้เล่นในเทิร์นนี้รึป่าว
        return playerState;
    }

    public void UpdatedStats() { // ทุกครั้งที่ใช้ จะเพิ่มเงิน กริ๊งๆ
        coin += hexInterest() + Main.turnInterest;
    }

    private int hexInterest() {
        return (ownHex.length > 0) ? (int) (ownHex.length * ownHex[0].interest) : 0;
    }

    public void setDefaultHex() { //เอาไว้ให้ Hex เริ่มต้นของตัวละคร
        System.out.println(name + " has been assigned starting hex.");
    }

    public void getMinion() {
        if (isPlayerTurn()) {
            System.out.println(name + " summons a minion.");
        }
    }

    public void buyHex(Hex hex) {
        if (phase.equals("BuyHex") && coin >= hex.cost) {
            coin -= hex.cost;
            hex.owner = this;
            System.out.println(name + " bought a hex!");
        } else {
            System.out.println("Cannot buy this hex.");
        }
    }
}
