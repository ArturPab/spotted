package pl.pabjan.spotted.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import pl.pabjan.spotted.model.Post;
import pl.pabjan.spotted.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {

    private Long commentId;
    private String content;
    private String postTitle;
}
