package pl.pabjan.spotted.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.spotted.controller.dto.LoginRequest;
import pl.pabjan.spotted.controller.dto.RegisterRequest;
import pl.pabjan.spotted.service.AuthService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("Pomyślnie zarejestrowano! Sprawdź skrzynkę pocztową", HttpStatus.OK);
    }

    @GetMapping("/api/auth/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Konto zostało aktywowane!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
    }
}
