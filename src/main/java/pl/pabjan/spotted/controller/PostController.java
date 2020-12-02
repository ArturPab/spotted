package pl.pabjan.spotted.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.spotted.controller.dto.PostRequest;

@RestController("/api/posts")
public class PostController {

    @PostMapping
    public void save(@RequestBody PostRequest postRequest) {

    }
}
