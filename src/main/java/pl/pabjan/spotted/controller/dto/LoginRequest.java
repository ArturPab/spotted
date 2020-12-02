package pl.pabjan.spotted.controller.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
