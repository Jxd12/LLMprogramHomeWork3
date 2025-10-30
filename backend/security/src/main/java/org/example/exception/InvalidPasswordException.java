package org.example.exception;

public class InvalidPasswordException extends AuthException {
    public InvalidPasswordException() {
        super("Invalid password");
    }
}