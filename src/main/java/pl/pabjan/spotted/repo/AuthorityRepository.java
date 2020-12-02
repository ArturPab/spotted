package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
