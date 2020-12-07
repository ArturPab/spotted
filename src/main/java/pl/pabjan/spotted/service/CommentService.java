package pl.pabjan.spotted.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.spotted.controller.dto.CommentRequest;
import pl.pabjan.spotted.controller.dto.CommentResponse;
import pl.pabjan.spotted.exceptions.SpottedException;
import pl.pabjan.spotted.mapper.CommentMapper;
import pl.pabjan.spotted.model.Comment;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;
import pl.pabjan.spotted.repo.CommentRepository;
import pl.pabjan.spotted.repo.PostRepository;
import pl.pabjan.spotted.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    public Comment save(CommentRequest commentRequest) {
        User user = authService.getCurrentUser();
        Post post = postRepository.findByTitle(commentRequest.getPostTitle());
        return commentRepository.save(commentMapper.map(commentRequest, user, post));
    }

    public CommentResponse getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new SpottedException("Nie znaleziono komentarza"));
        return commentMapper.mapToDto(comment);
    }

    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }


    public List<CommentResponse> getCommentsByPost(String title) {
        Post post = postRepository.findByTitle(title);
        List<Comment> comments = commentRepository.findByPost(post);
        return comments
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentResponse> getCommentsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpottedException("Nie znaleziono użytkownika"));
        List<Comment> comments = commentRepository.findByUser(user);
        return comments
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
