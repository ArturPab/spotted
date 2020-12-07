package pl.pabjan.spotted.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pabjan.spotted.controller.dto.CommentRequest;
import pl.pabjan.spotted.controller.dto.CommentResponse;
import pl.pabjan.spotted.model.Comment;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "content", source = "commentRequest.content")
    Comment map(CommentRequest commentRequest, User user, Post post);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postTitle", source = "post.title")
    CommentResponse mapToDto(Comment comment);
}
