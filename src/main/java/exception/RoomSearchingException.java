package exception;

public class RoomSearchingException extends BaseException{
    public RoomSearchingException(String code) {
        super("room." + code);
    }

    public static RoomSearchingException idNull() {
        return new RoomSearchingException("id.null");
    }

    public static RoomSearchingException notFound() {
        return new RoomSearchingException("notFound");
    }
}