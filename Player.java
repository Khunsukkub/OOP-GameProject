public class Player {
    private String name;
    public int team;
    private int coin;
    private int[][] ownHex;
    private Minion ownMinion;

    public Player(int coin) {
        this.coin = coin;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDefaultHex(){ //คอยให้ช่องเริ่มต้นของผู้เล่นทั้งสองฝั่ง
        if(team == 1) {

        } else {

        }
    }

    public void getMinion(){}
}
