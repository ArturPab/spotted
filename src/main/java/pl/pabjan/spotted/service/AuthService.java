package pl.pabjan.spotted.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.spotted.controller.dto.RegisterRequest;
import pl.pabjan.spotted.exceptions.SpottedException;
import pl.pabjan.spotted.model.NotificationEmail;
import pl.pabjan.spotted.model.User;
import pl.pabjan.spotted.model.VerificationToken;
import pl.pabjan.spotted.repo.UserRepository;
import pl.pabjan.spotted.repo.VerificationTokenRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Aktywuj swoje konto", user.getEmail(),
                "Dziekujemy za rejestracje w serwisie Spotted! Kliknij w ponizszy link aby aktywowac konto: " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpottedException("Nieprawidłowy link aktywacyjny"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpottedException("Nie znaleziono użytkownika " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
