// src/main/java/org/example/exception/SystemException.java
package org.example.exception;

public class SystemException extends BaseException {
    public SystemException(String message) {
        super(500, message);
    }
    
    public SystemException(int code, String message) {
        super(code, message);
    }
}
