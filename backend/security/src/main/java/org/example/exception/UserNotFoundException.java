package org.example.exception;

// 用户未找到异常
public class UserNotFoundException extends AuthException {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}






