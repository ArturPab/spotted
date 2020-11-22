package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<Long, VerificationToken> {
}
