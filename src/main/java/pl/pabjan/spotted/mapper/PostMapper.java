package pl.pabjan.spotted.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pabjan.spotted.controller.dto.PostRequest;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, User user);
}
