public class Player {
    private String name;
    public int team;
    private Coin coin;

    public Player(int initialCoin) {
        this.coin = new Coin(initialCoin);
    }

    public String getName() {
        return name;
    }

    public int getTeam() {
        return team;
    }

    public Coin getCoinObject() { //ใช้งาน Coin โดยตรง
        return this.coin;
    }
}
