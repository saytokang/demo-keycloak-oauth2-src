package com.example.demooauth;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/token")
    public ResponseEntity<?> token(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().body(jwt.getTokenValue());
    }

    @GetMapping("/user")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok().body(String.format("user : %s", new Date()));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok().body(String.format("admin : %s", new Date()));
    }
}
