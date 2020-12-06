package pl.pabjan.spotted.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostResponse {

    private Long postId;
    private String title;
    private String description;
    private String city;
    private String url;
    private String username;

}
