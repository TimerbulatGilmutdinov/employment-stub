package ru.itis.employmentstub.exception;

public class UnknownStatusException extends RuntimeException{
    public UnknownStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownStatusException(String message) {
        super(message);
    }

    public UnknownStatusException(Throwable cause) {
        super(cause);
    }
}
