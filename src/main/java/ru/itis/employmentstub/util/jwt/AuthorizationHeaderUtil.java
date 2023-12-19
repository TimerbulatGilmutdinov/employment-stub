package ru.itis.employmentstub.util.jwt;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderUtil {
    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);
}
