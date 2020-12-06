package pl.pabjan.spotted.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.spotted.controller.dto.PostRequest;
import pl.pabjan.spotted.controller.dto.PostResponse;
import pl.pabjan.spotted.service.PostService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<PostResponse>> getByUsername(@PathVariable String username) {
        return status(HttpStatus.OK).body(postService.getByUsername(username));
    }

    @GetMapping("/by-city/{city}")
    public ResponseEntity<List<PostResponse>> getByCity(@PathVariable String city) {
        return status(HttpStatus.OK).body(postService.getByCity(city));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<PostResponse> getByTitle(@PathVariable String title) {
        return status(HttpStatus.OK).body(postService.getByTitle(title));
    }

}
