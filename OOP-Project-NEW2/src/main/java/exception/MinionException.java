package exception;

public class MinionException extends BaseException {
    public MinionException(String code) {
        super("minion." + code);
    }

    public static MinionException nameNull() {
        return new MinionException("register.name.null");
    }
    public static MinionException hpNull() {
        return new MinionException("register.hp.null");
    }
    public static MinionException defNull() {
        return new MinionException("register.def.null");
    }
}
