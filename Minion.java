public class Minion {
    private String name;
    private int level;
    private int cost;
    private int hp;
    private int mp;
    private int maxHp;
    private int maxMp;
    private int ATK;
    private int DEF;
    private int moveRange;
    private int atkRange;

    public Minion(int level, int cost , int maxHp , int maxMp , int ATK , int DEF , int moveRange , int atkRange ) {
        this.level = level;
        this.cost = cost;
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.mp = 0;
        this.ATK = ATK;
        this.DEF = DEF;
        this.moveRange = moveRange;
        this.atkRange = atkRange;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void Levelup() {}
    public void attackTo(Minion enemy) {}
    public void moveTo(Hex hexNumber) {}

}
