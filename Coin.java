public class Coin {
    private int amount; // จำนวนเหรียญของผู้เล่น

    public Coin(int initialAmount) {
        this.amount = Math.max(initialAmount, 0); // ป้องกันค่าติดลบ
    }

    public int getAmount() {
        return amount;
    }

    public void add(int value) {
        if (value > 0) {
            this.amount += value;
        } else {
            System.out.println("Invalid value! Value must be positive.");
        }
    }

    public boolean spend(int value) {
        if (value > 0 && this.amount >= value) {
            this.amount -= value;
            return true; // ใช้เหรียญสำเร็จ
        } else {
            System.out.println("Not enough coins!");
            return false; // ใช้เหรียญไม่สำเร็จ
        }
    }

    public boolean hasEnough(int value) {
        return this.amount >= value;
    }
}
