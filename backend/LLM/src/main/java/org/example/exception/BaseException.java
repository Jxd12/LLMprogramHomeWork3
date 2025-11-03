// src/main/java/org/example/exception/BaseException.java
package org.example.exception;

public class BaseException extends RuntimeException {
    private int code;
    private String message;
    
    public BaseException(String message) {
        super(message);
        this.message = message;
    }
    
    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
