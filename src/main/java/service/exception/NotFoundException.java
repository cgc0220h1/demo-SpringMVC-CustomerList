package service.exception;

public class NotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "entity Not Found";
    }
}
