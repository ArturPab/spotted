package pl.pabjan.spotted.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.spotted.controller.dto.PostRequest;
import pl.pabjan.spotted.mapper.PostMapper;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;
import pl.pabjan.spotted.repo.PostRepository;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PostService{

    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save(PostRequest postRequest) {
        return postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }


}
