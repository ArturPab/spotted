package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.User;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
}
