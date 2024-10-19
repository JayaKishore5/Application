package com.example.application.controller;

import com.example.application.User;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Login method to handle both form data and JSON requests
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestBody(required = false) LoginRequest loginRequest
    ) {
        if (loginRequest != null) {
            email = loginRequest.getEmail();
            password = loginRequest.getPassword();
        }

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }

        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }

    // Redirect to login page
    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("redirect:/login.html");
    }

    public static class LoginRequest {
        private String email;
        private String password;
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
