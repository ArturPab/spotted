package pl.pabjan.spotted.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.spotted.controller.dto.PostRequest;
import pl.pabjan.spotted.controller.dto.PostResponse;
import pl.pabjan.spotted.exceptions.SpottedException;
import pl.pabjan.spotted.mapper.PostMapper;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;
import pl.pabjan.spotted.repo.PostRepository;
import pl.pabjan.spotted.repo.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PostService{

    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public Post save(PostRequest postRequest) {
        return postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }


    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }


    public List<PostResponse> getByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpottedException("Nie znaleziono użytkownika"));
        List<Post> posts = postRepository.findByUser(user);

        return posts
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public List<PostResponse> getByCity(String city) {
        List<Post> posts = postRepository.findByCity(city);

        return posts
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());

    }

    public PostResponse getByTitle(String title) {
        Post post = postRepository.findByTitle(title);

        return postMapper.mapToDto(post);
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpottedException("Nie znaleziono użytkownika!"));

        return postMapper.mapToDto(post);
    }
}
