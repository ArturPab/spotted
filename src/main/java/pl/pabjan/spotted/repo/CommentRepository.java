package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
