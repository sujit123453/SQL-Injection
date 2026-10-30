package com.blueThink.SQLi.controller;

import com.blueThink.SQLi.dto.LoginRequest;
import com.blueThink.SQLi.service.impl.VulnerableLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class VulnerableLoginController {
    private final VulnerableLoginService vulnerableLoginService;
    public VulnerableLoginController(VulnerableLoginService vulnerableLoginService){
        this.vulnerableLoginService = vulnerableLoginService;
    }

    @PostMapping("/login-vulnerable")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        boolean result = vulnerableLoginService.executeUnsafeQuery(
                request.getUserName(),
                request.getPassword()
        );

        if (result) {
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(401)
                .body("Invalid credentials");
    }
}
