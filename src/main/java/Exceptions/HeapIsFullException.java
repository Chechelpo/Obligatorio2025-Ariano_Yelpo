package Exceptions;

public class HeapIsFullException extends RuntimeException {
    public HeapIsFullException(String message) {
        super(message);
    }
}
