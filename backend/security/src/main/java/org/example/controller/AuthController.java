package org.example.controller;

import org.example.entity.VO.UserSecurityVO;
import org.example.exception.InvalidPasswordException;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(produces = "application/json", consumes = "application/json")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<UserSecurityVO>> login(@RequestBody UserSecurityVO user) {
        System.out.println("Received login request: " + user);
        try {
            UserSecurityVO loggedInUser = userService.login(user);
            return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", loggedInUser));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Login failed: " + e.getMessage(), null));
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<UserSecurityVO>> register(@RequestBody UserSecurityVO user) {
        System.out.println("Received register request: ");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println("----------------------------");
        user.setRole("USER");
        try {
            UserSecurityVO registeredUser = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Registration successful", registeredUser));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Registration failed: " + e.getMessage(), null));
        }
    }
}
