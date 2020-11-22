package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Long, Post> {
}
