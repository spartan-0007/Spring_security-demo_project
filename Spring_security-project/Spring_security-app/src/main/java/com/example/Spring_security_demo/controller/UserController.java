package com.example.Spring_security_demo.controller;

import com.example.Spring_security_demo.model.User;
import com.example.Spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController// This Lombok annotation automatically creates a static final Logger field named 'log'
public class UserController {

    // With @Slf4j, you don't need to manually declare the logger like this:
    // private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("register")
    public User register(@RequestBody User user){
        log.info("Received request to register user: {}", user.getUsername()); // Log incoming request
        User savedUser = service.saveUser(user);
        log.info("User registered successfully with ID: {}", savedUser.getId()); // Log successful registration
        return savedUser;
    }

    @PostMapping("login")
    public String login(@RequestBody User user){

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));


        if(authentication.isAuthenticated())
            return "true";
        else
            return "Login failed";
    }
}
