public class Minion {
    public String name;
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
    private Hex position;

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
        this.position = null;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void Levelup() {
        level++;
        maxHp += 10;
        hp = maxHp;
        ATK += 2;
        DEF += 1;
    }

    public void attackTo(Minion enemy) {

        if(this.atkRange >= getDistance(this.position, enemy.position)) {
            int damage = this.ATK-(enemy.DEF*4/10);
            enemy.hp -= damage;
            if(enemy.hp <= 0) enemy.hp = 0;
        } else

            System.out.println("Out of Range");

    }

    public void moveTo(Hex hex) {
        if (hex.owner.name.equals(this.name) && getDistance(this.position, hex) <= moveRange) {
            this.position = hex;
            System.out.println(name + " moved to a new position.");
        } else {
            System.out.println("Invalid move.");
        }
    }

    private int getDistance(Hex a, Hex b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Example distance calculation
    }

}
