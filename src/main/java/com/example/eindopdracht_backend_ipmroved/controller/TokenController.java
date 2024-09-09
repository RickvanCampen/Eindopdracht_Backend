package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Token;
import com.example.eindopdracht_backend_ipmroved.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Token>> getAllTokens() {
        List<Token> tokens = tokenService.getAllTokens();
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable int id) {
        Optional<Token> token = tokenService.getTokenById(id);
        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/value/{tokenValue}")
    public ResponseEntity<Token> getTokenByTokenValue(@PathVariable String tokenValue) {
        Optional<Token> token = tokenService.getTokenByTokenValue(tokenValue);
        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        Token savedToken = tokenService.saveToken(token);
        return ResponseEntity.ok(savedToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Token> updateToken(@PathVariable int id, @RequestBody Token token) {
        if (tokenService.getTokenById(id).isPresent()) {
            token.setId(id);
            Token updatedToken = tokenService.saveToken(token);
            return ResponseEntity.ok(updatedToken);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable int id) {
        if (tokenService.getTokenById(id).isPresent()) {
            tokenService.deleteToken(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
