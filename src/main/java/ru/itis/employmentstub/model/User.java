package ru.itis.employmentstub.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String accessToken;
    private String sub;
    private Date iat;
    private Date exp;
    private String scope;
    private List<Role> roles;
    private String sessionId;
    private String sessionHash;
    private Long allId;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Role {
        private String login;
        private Long id;
        private String type;
    }
}
