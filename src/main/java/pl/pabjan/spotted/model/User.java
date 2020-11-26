package pl.pabjan.spotted.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Nazwa użytkownika jest wymagana!")
    private String username;

    @NotBlank(message = "Hasło jest wymagane!")
    private String password;

    @NotBlank(message = "E-mail jest wymagany!")
    private String email;

    private Instant created;
    private boolean enabled;
}
