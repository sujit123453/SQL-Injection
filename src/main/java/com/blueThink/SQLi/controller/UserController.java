package com.blueThink.SQLi.controller;

import com.blueThink.SQLi.dto.UserDTO;
import com.blueThink.SQLi.entity.User;
import com.blueThink.SQLi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody  User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestParam String userName,
                                         @RequestParam String password){
        return new ResponseEntity<>(userService.loginUser(userName, password), HttpStatus.OK);
    }
}
