package exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user." + code);
    }

    public static UserException nameNull() {
        return new UserException("register.name.null");
    }
}