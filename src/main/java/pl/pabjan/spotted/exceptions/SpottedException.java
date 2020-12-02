package pl.pabjan.spotted.exceptions;

public class SpottedException extends RuntimeException {
    /*public SpottedException(String message, Exception e) {
        super(message, e);
    }*/
    public SpottedException(String message) {
        super(message);
    }
}
