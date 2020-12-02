package pl.pabjan.spotted.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.spotted.config.RestAuthenticationSuccessHandler;
import pl.pabjan.spotted.controller.dto.LoginRequest;
import pl.pabjan.spotted.controller.dto.LoginResponse;
import pl.pabjan.spotted.controller.dto.RegisterRequest;
import pl.pabjan.spotted.exceptions.SpottedException;
import pl.pabjan.spotted.model.NotificationEmail;
import pl.pabjan.spotted.model.User;
import pl.pabjan.spotted.model.VerificationToken;
import pl.pabjan.spotted.repo.RoleRepo;
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
    private final RoleRepo roleRepo;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        user.setRoles(roleRepo.findByName("USER"));

        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Aktywuj swoje konto", user.getEmail(),
                "Dziekujemy za rejestracje w serwisie Spotted! Kliknij w ponizszy link aby aktywowac konto: " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    //Tworzy token aktywujący konto
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

    //Aktywuje użytkownika
    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpottedException("Nie znaleziono użytkownika " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

//    public LoginResponse getAuthorization(LoginRequest loginRequest) {
//        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Niepoprawny login lub hasło"));
//        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
//            return new LoginResponse(loginRequest.getUsername(), successHandler.getJwtToken());
//        else
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//                loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        return new LoginResponse(loginRequest.getUsername(), successHandler.getJwtToken());
//
//    }

}
