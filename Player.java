public class Player {
    private String name;
    public boolean playerState; // เช็ค state ถ้า True แปลว่าถึงตาที่เล่นแล้ว & False คือไม่ถึง Turn
    public int team;
    private int coin;
    private int[][] ownHex;
    private Minion ownMinion;

    public Player(int coin) {
        this.coin = coin;
        if ((team == 1)) {
            playerState = true;
        } else playerState = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDefaultHex(){ //คอยให้ช่องเริ่มต้นของผู้เล่นทั้งสองฝั่ง
        if(team == 1) {

        } else {

        }
    }

    public void getMinion(){
        if(playerState == true) {

        }
    }
}