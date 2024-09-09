package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Token;
import com.example.eindopdracht_backend_ipmroved.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    public Optional<Token> getTokenById(int id) {
        return tokenRepository.findById((long) id);
    }

    public Optional<Token> getTokenByTokenValue(String tokenValue) {
        return tokenRepository.findByToken(tokenValue);
    }

    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    public void deleteToken(int id) {
        tokenRepository.deleteById((long) id);
    }
}
