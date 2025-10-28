package com.example.backend.controller;

import com.example.backend.utils.CreationUser;
import com.example.backend.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        try {
            String email = body.get("email");
            String password = body.get("password");
            boolean created = CreationUser.creerUser(email, password);
            if (created) {
                response.put("message", "utilisateur creer bienvenue!");
            } else {
                response.put("message", "utilisateur existe deja");
            }
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String email, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        try {
            String token = CreationUser.login(email, password);
            if (token != null) {
                response.put("token", token);
            } else {
                response.put("error", "mauvais email ou mot de passe ");
            }
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/verify")
    public Map<String, String> verify(@RequestHeader("Authorization") String authHeader) {
        Map<String, String> response = new HashMap<>();
        // bearer necessaire pour le token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = JwtUtil.verifierToken(token);
            if (email != null) {
                response.put("email", email);
            } else {
                response.put("error", "token invalide ou exprier");
            }
        } else {
            response.put("error", "manque le header ");
        }
        return response;
    }
}
