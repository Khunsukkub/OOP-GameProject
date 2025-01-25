public class Hex {
    private int[][] Hex;
    private Player owner;
    private int cost;
    public double interest;
    private String color;

    public Hex() {
        this.Hex = new int[8][8];
        this.cost = 1000;
        this.interest = 50;
        this.color = "FFFFF";
    }

    public int encodePosition(int row, int col) { // เอาไว้เก็บใน array บอกตำแหน่ง
        return row * 10 + col; // ตัวอย่างการเข้ารหัส เช่น row=1, col=2 จะเป็น 12
    }

    public void hexState(){} //เอาไว้เช็คว่าช่องนี้เป็นของใคร และ คอยเปลี่ยนสี

}