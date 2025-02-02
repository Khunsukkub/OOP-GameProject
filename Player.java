public class Player {
    String name;
    public boolean playerState; // True when it's the player's turn
    public int team;
    private int coin;
    public Hex[] ownHex;
    private Minion ownMinion;
    public String phase;

    public Player(int team, int coin) {
        this.team = team;
        this.coin = coin;
        this.ownHex = new Hex[0]; // Initialize empty array
        this.playerState = (team == 1); // First player gets the turn
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlayerTurn() {
        return playerState;
    }

    public void UpdatedStats() {
        coin += hexInterest() + Main.turnInterest;
    }

    private int hexInterest() {
        return (ownHex.length > 0) ? (int) (ownHex.length * ownHex[0].interest) : 0;
    }

    public void setDefaultHex() {
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
