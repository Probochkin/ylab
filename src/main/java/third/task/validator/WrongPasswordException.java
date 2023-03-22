package third.task.validator;

public class WrongPasswordException extends Exception {


    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
