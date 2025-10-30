package org.example.exception;

// 用户已存在异常
public class UserAlreadyExistsException extends AuthException {
    public UserAlreadyExistsException(String username) {
        super("User already exists: " + username);
    }
}
