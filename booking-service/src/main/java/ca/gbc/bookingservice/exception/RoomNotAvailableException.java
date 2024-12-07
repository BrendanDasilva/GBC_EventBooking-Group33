package ca.gbc.bookingservice.exception;
public class RoomNotAvailableException extends RuntimeException {
    // Constructor that accepts a custom message
    public RoomNotAvailableException(String message) {
        super(message);
    }
    // Constructor that accepts both a custom message and a cause (for exception chaining)
    public RoomNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}