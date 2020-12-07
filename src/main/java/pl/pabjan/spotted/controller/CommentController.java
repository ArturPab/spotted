package pl.pabjan.spotted.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.spotted.controller.dto.CommentRequest;
import pl.pabjan.spotted.controller.dto.CommentResponse;
import pl.pabjan.spotted.model.Comment;
import pl.pabjan.spotted.service.CommentService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody CommentRequest commentRequest) {
        commentService.save(commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {
        return status(HttpStatus.OK).body(commentService.getComment(id));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        return status(HttpStatus.OK).body(commentService.getAllComments());
    }

    @GetMapping("/by-post/{title}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable String title) {
        return status(HttpStatus.OK).body(commentService.getCommentsByPost(title));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<CommentResponse>> getCommentsByUsername(@PathVariable String username) {
        return status(HttpStatus.OK).body(commentService.getCommentsByUsername(username));
    }
}
